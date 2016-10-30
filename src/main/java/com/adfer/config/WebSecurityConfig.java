package com.adfer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by adrianferenc on 10.10.2016.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/admin**").fullyAuthenticated()
                .and()
            .authorizeRequests()
                .antMatchers("/admin/**").fullyAuthenticated()
                .and()
            .authorizeRequests()
                .anyRequest().permitAll()
                .and()
        .formLogin()
                .loginPage("/login").permitAll()
                .and()
        .logout()
                .permitAll()
                .and()
        .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("greta").password("747Addison!").roles("ADMIN");
    }
}