package com.example.unsecureapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class BasicHttpConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser( "Admin").password(passwordEncoder().encode("admin123")).roles("ADMIN")
                .and()
                .withUser("Agent").password(passwordEncoder().encode( "agent123")).roles("AGENT")
                .and()
                .withUser("Customer").password(passwordEncoder().encode("customer123")).roles("CUSTOMER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("index.html").permitAll()
                .antMatchers("/customer/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/agent/**").hasRole("AGENT")
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
