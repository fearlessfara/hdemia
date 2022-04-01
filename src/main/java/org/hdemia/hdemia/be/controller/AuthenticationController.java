package org.hdemia.hdemia.be.controller;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.hdemia.hdemia.be.controller.dto.KeepAliveResponseDTO;
import org.hdemia.hdemia.be.controller.dto.LoginRequestDTO;
import org.hdemia.hdemia.be.controller.dto.LoginResponseDTO;
import org.hdemia.hdemia.be.controller.dto.RegistrationRequestDTO;
import org.hdemia.hdemia.be.controller.dto.RegistrationResponseDTO;
import org.hdemia.hdemia.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public RegistrationResponseDTO register(@RequestBody RegistrationRequestDTO request) {
        Preconditions.checkArgument(!StringUtils.isBlank(request.name()), "name cannot be blank");
        Preconditions.checkArgument(!StringUtils.isBlank(request.surname()), "surname cannot be blank");
        Preconditions.checkArgument(!StringUtils.isBlank(request.email()), "email cannot be blank");
        Preconditions.checkArgument(!StringUtils.isBlank(request.password()) && request.password().length() >= 8, "password does not match criteria");
        Preconditions.checkNotNull(request.regNumber(), "registration number cannot be blank");

        //TODO preconditions
        boolean registered = userService.register(request);
        return new RegistrationResponseDTO(registered);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        //TODO preconditions
        return null;
    }

    @PostMapping("/keepAlive")
    public KeepAliveResponseDTO keepAlive(HttpServletRequest request) {
        return null;
    }


}
