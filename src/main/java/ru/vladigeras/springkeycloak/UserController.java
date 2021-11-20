package ru.vladigeras.springkeycloak;

import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/anonymous")
    public String getAnonymousInfo() {
        return "Anonymous";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String getUserInfo() {
        return "User info";
    }

    // not working with ROLE_ADMIN, because by default authorities creates from SCOPE_ in this case
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminInfo() {
        return "Admin info";
    }

    @SneakyThrows
    @GetMapping("/logout")
    public String logout(HttpServletRequest rq) {
        rq.logout();
        return "Logout";
    }

    @GetMapping("/me")
    public Authentication getMe(Authentication authentication) {
        return authentication;
    }
}
