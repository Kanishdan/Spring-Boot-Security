package com.example.rolebasedsecurity.configuration;

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
public class RoleBasedConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")//.authorities("ACCESS_TEST1", "ACCESS_TEST2")
                .and()
                .withUser("agent").password(passwordEncoder().encode("agent123"))
                .roles("AGENT")//.authorities("ACCESS_TEST1")
                .and()
                .withUser("customer").password(passwordEncoder().encode("customer123"))
                .roles("CUSTOMER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/customer/**").hasRole("CUSTOMER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/agent/**").hasRole("AGENT")
                //.antMatchers("/api/public/**").hasRole("ADMIN")
//                .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
//                .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
                .and()
                .httpBasic();
//            .authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .and()
//                .formLogin();

    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
