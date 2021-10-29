package com.maththomaz.animeapibydevdojo.config;

import com.maththomaz.animeapibydevdojo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Log4j2
@RequiredArgsConstructor
@SuppressWarnings("java:S5344")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
//
//    BasicAuthenticationFilter
//    UsernamePasswordAuthenticationFilter
//    DefaultLoginPageGeneratingFilter
//    DefaultLogoutPageGeneratingFilter
//    FilterSecurityInterceptor
//    Authentication -> Authorization
//
//    @param http
//    @throws Exception
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
//                Para habilitar o CSRF Token, comentar o disable() e habilitar as duas linhas abaixo:
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .authorizeRequests()
                .antMatchers("/api/v1/animes/admin/**").hasRole("ADMIN")
                .antMatchers("/api/v1/animes/**").hasRole("USER")
                .antMatchers("/actuator/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("Password encoded {}", passwordEncoder.encode("salaal"));

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("salaal"))
                .roles("USER", "ADMIN")
                .and()
                .withUser("user")
                .password(passwordEncoder.encode("salaal"))
                .roles("USER");

        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }
}
