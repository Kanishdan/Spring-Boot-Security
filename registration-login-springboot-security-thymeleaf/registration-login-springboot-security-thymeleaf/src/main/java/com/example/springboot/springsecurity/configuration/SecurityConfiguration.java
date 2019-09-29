package com.example.springboot.springsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.springboot.springsecurity.service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserService userService;
	

    @Override
    protected void configure(HttpSecurity http) throws Exception { //contains information about how to authenticate our users.How does Spring Security know that we want to require all users to be authenticated? How does Spring Security know we want to support form based authentication? 
        http
                .authorizeRequests()
                    .antMatchers(
                            "/registration**",
                            "/js/**",
                            "/css/**",
                            "/img/**",
                            "/webjars/**").permitAll()
                    .anyRequest().authenticated() //Ensures that any request to our application requires the user to be authenticated
//                    .antMatchers("/index.html").permitAll()
//                    .antMatchers("/login.html")
                    .and()
                    .formLogin() //Allows users to authenticate with form based login
                        .loginPage("/login") //The updated configuration specifies the location of the log in page.
                            .permitAll()   //We must grant all users (i.e. unauthenticated users) access to our log in page.
                .and()
                    .logout()
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
