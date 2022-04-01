package org.hdemia.hdemia.be.controller.v1;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.hdemia.hdemia.be.controller.dto.KeepAliveResponseDTO;
import org.hdemia.hdemia.be.controller.dto.LoginRequestDTO;
import org.hdemia.hdemia.be.controller.dto.LoginResponseDTO;
import org.hdemia.hdemia.be.controller.dto.RegistrationRequestDTO;
import org.hdemia.hdemia.be.controller.dto.RegistrationResponseDTO;
import org.hdemia.hdemia.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("v1/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Value("${site.url:google.com}")
    String siteHomepage;

    @PostMapping("/register")
    public RegistrationResponseDTO register(@RequestBody RegistrationRequestDTO request) {
        Preconditions.checkArgument(!StringUtils.isBlank(request.name()), "name cannot be blank");
        Preconditions.checkArgument(!StringUtils.isBlank(request.surname()), "surname cannot be blank");
        Preconditions.checkArgument(!StringUtils.isBlank(request.email()), "email cannot be blank");
        Preconditions.checkArgument(!StringUtils.isBlank(request.password()) && request.password().length() >= 8, "password does not match criteria");
        Preconditions.checkNotNull(request.regNumber(), "registration number cannot be blank");

        userService.register(request);
        return new RegistrationResponseDTO(true);
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

    @GetMapping("/verify/{verificationToken}")
    public ModelAndView verify(@PathVariable("verificationToken") String verificationToken, ModelMap model) {
        boolean verified = userService.verify(verificationToken);
        model.addAttribute("verified", verified);
        return new ModelAndView("redirect: " + siteHomepage, model);

    }


}
