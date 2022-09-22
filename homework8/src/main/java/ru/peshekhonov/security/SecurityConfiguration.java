package ru.peshekhonov.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    public void authConfig(
            AuthenticationManagerBuilder authBuilder,
            VisitorDetailsServiceImpl visitorDetailsService,
            PasswordEncoder encoder
    ) throws Exception {
        authBuilder.userDetailsService(visitorDetailsService);
    }
}
