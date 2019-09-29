package com.example.rolebasedsecurity.configuration;

import com.example.rolebasedsecurity.service.MyUserDetails;
import com.example.rolebasedsecurity.service.MyUserDetailsImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class RoleBasedConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetails myUserDetails;
    public RoleBasedConfiguration(MyUserDetails myUserDetails)
    {
        this.myUserDetails=myUserDetails;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authenticationProvider());
//                .inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("admin123"))
//                .roles("ADMIN").authorities("ACCESS_TEST1", "ACCESS_TEST2","ROLE_ADMIN")
//                .and()
//                .withUser("agent").password(passwordEncoder().encode("agent123"))
//                .roles("AGENT").authorities("ACCESS_TEST1","ROLE_MANAGER")
//                .and()
//                .withUser("customer").password(passwordEncoder().encode("customer123"))
//                .roles("CUSTOMER");

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
                .antMatchers("/api/public/users").hasRole("ADMIN")
              //  .antMatchers("/api/public/**").hasRole("ADMIN")
                .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
                .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
                .and()
//                .httpBasic();
                .formLogin()
                .loginProcessingUrl("/signIn")
                .loginPage("/login").permitAll()
                .usernameParameter("txtUsername")
                .passwordParameter("txtPassword")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .and()
                .rememberMe().tokenValiditySeconds(86400).key("tokenKey");

    }

    @Bean
    DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.myUserDetails);
        return daoAuthenticationProvider;
    }
    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
