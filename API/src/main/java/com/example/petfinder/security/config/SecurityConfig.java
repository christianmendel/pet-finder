package com.example.petfinder.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.web.http.HttpSessionIdResolver;

import static org.springframework.session.web.http.HeaderHttpSessionIdResolver.xAuthToken;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return xAuthToken();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()

                // deixa qualquer url com /publico como pública
                .authorizeRequests().antMatchers("/*/publico").permitAll().and()

                // deixa POST /login e /usuarios público
                .authorizeRequests().antMatchers(HttpMethod.POST, "/login", "/cadastro").permitAll().and()

                // todas as urls devem ser acessadas por um usuário autenticado (privadas)
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
