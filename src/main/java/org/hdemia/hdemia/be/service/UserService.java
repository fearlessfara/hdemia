package org.hdemia.hdemia.be.service;

import org.hdemia.hdemia.be.controller.dto.RegistrationRequestDTO;
import org.hdemia.hdemia.model.entity.Credential;
import org.hdemia.hdemia.model.entity.User;
import org.hdemia.hdemia.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static java.util.Objects.isNull;

@Component
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public boolean register(RegistrationRequestDTO request) {

        Credential c = new Credential();
        c.setUsername(request.email());
        c.setPassword(passwordEncoder.encode(request.password()));

        User user = User.Builder.anUser()
                .withEmail(request.email())
                .withName(request.name())
                .withMiddleName(request.middleName())
                .withCredential(c)
                .build();

        user = userRepository.save(user);
        return true;
    }
}
