package io.jay.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(configurer -> configurer.disable());

        // for h2-console
        http.authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(toH2Console())
                                .permitAll())
                .headers(headers -> headers.frameOptions(withDefaults()).disable());

        // authenticate all other requests
        http.authorizeHttpRequests(authorize ->
                authorize
                        .anyRequest()
                        .authenticated()
        );

        // basic authentication
        http.httpBasic(withDefaults());

        // exception handling
        http.exceptionHandling(configurer -> configurer.authenticationEntryPoint(authenticationEntryPoint));

        http.authenticationProvider(authenticationProvider);

        return http.build();
    }
}
