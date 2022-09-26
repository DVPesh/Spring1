package ru.peshekhonov.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

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

    @Configuration
    public static class UiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/**/*.css", "/**/*.js").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/visitor/**").hasAnyRole("ADMIN", "SUPER")
                    .and()
                    .formLogin()
                    .successHandler((request, response, authentication) -> {
                        Set<String> auths = authentication.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toSet());
                        if (auths.contains("ROLE_ADMIN") || auths.contains("ROLE_SUPER")) {
                            response.sendRedirect("/visitor");
                        } else {
                            response.sendRedirect("/product");
                        }
                    })
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/access_denied");

        }
    }

}
