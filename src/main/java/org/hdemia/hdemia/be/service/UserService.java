package org.hdemia.hdemia.be.service;

import org.hdemia.hdemia.be.controller.dto.RegistrationRequestDTO;
import org.hdemia.hdemia.model.entity.Credential;
import org.hdemia.hdemia.model.entity.User;
import org.hdemia.hdemia.model.entity.VerificationTokenStatus;
import org.hdemia.hdemia.model.repository.UserRepository;
import org.hdemia.hdemia.model.repository.VerificationTokenStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;

    @Autowired
    VerificationTokenStatusRepository verificationTokenStatusRepository;

    @Transactional
    public User register(RegistrationRequestDTO request) {

        Credential c = new Credential();
        c.setUsername(request.email());
        c.setPassword(passwordEncoder.encode(request.password()));

        User user = User.Builder.anUser()
                .withEmail(request.email())
                .withName(request.name())
                .withMiddleName(request.middleName())
                .withSurname(request.surname())
                .withCredential(c)
                .withVerified(false)
                .build();

        //TODO complete this
        mailService.send(user.getEmail(), "hdemia verification", "press ALT + F4 to verify your hdemia account.");
        return userRepository.save(user);
    }

    public boolean verify(String verificationToken) {
        VerificationTokenStatus token = verificationTokenStatusRepository.findByToken(verificationToken).orElseThrow(() -> new IllegalArgumentException("verification token not found"));
        if (token.isVerified()) {
            throw new IllegalStateException("This token has already been verified");
        }
        User user = userRepository.findById(token.getUserId()).orElseThrow(() -> new IllegalStateException("user associated with token not found"));
        user.setVerified(true);
        token.setVerified(true);
        user = userRepository.save(user);
        token = verificationTokenStatusRepository.save(token);
        return false;
    }
}
