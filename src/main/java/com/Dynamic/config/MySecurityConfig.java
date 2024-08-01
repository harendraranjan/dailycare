package com.Dynamic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	String[] permitted = new String[]{
                "/", "/home","/register","/about","/png/**",
                "/css/**","/icons/**","/img/**","/js/**","/lib/**","/scss/**","/restorant-1.0.0/**"
        };
    	http.csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
            	.requestMatchers(permitted).permitAll()	
                .requestMatchers("/user/**").hasRole("USER") 
                .requestMatchers("/admin/**").hasRole("ADMIN") 
                .anyRequest().permitAll() 
            )
            .formLogin().permitAll(); 

        return http.build();
    }

}
