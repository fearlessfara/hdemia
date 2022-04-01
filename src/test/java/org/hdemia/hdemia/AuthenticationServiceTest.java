package org.hdemia.hdemia;

import org.hdemia.hdemia.be.controller.AuthenticationController;
import org.hdemia.hdemia.be.controller.dto.RegistrationRequestDTO;
import org.hdemia.hdemia.be.controller.dto.RegistrationResponseDTO;
import org.hdemia.hdemia.be.service.UserService;
import org.hdemia.hdemia.model.entity.User;
import org.hdemia.hdemia.model.repository.CredentialRepository;
import org.hdemia.hdemia.model.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class AuthenticationServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CredentialRepository credentialRepository;

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    UserService userService;

    @BeforeEach
    public void init() {
        credentialRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void registerUserOK() {
        RegistrationRequestDTO request = new RegistrationRequestDTO("Domenico", "Pontari", "", 1, "d.pontari@elis.org", "ILoveJesus");
        RegistrationResponseDTO response = authenticationController.register(request);
        assertThat(response.success()).isEqualTo(true);

        User dom = userRepository.findByEmail(request.email()).orElseThrow(IllegalStateException::new);
        assertThat(dom.getCredential().getUsername()).isNotBlank();
        assertThat(dom.getCredential().getUsername()).isEqualToIgnoringCase(request.email());
        assertThat(dom.getCredential().getPassword()).isNotBlank();
        assertThat(dom.getCredential().getPassword()).isNotEqualToIgnoringCase(request.password());
    }

    @Test
    public void registerUserMissingParams() {
        RegistrationRequestDTO request = new RegistrationRequestDTO("", "Pontari", "", 1, "d.pontari@elis.org", "ILoveJesus");
        assertThrows(IllegalArgumentException.class, () -> authenticationController.register(request));

    }
}
