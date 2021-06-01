package com.MIW.Cohort5.Clups.configuration;

import com.MIW.Cohort5.Clups.services.implementations.ClupsUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * Decides who can do what
 */

@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        ClupsUserDetailsServiceImpl clupsUserDetailsService;

    public SecurityConfiguration(ClupsUserDetailsServiceImpl clupsUserDetailsService) {
        this.clupsUserDetailsService = clupsUserDetailsService;
    }

    //This defines the Admin user. This is a leakage in security and is code that will have to be replaced.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER", "ADMIN");
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                    .antMatchers("/css/**", "/webjars/**").permitAll() // These are general resources
//                    .antMatchers("/", "/products").permitAll() // These are the landing pages
                    .anyRequest().authenticated().and()
                .formLogin().and()
                .logout().logoutSuccessUrl("/products");
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(clupsUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
