/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda;

import org.springframework.context.annotation.Bean;

/**
 *
 * @author diego
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
     private final AppAuthenticationSuccessHandler successHandler;

    public SecurityConfig(AppAuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/persona", "/personasN").hasRole("ADMIN")
                .requestMatchers("/personas", "/").hasAnyRole("USER", "VENDEDOR", "ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login").permitAll()
                .successHandler(successHandler) // Aquí usamos nuestro handler
            );
        return http.build();
    }
    
    @Bean 
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
      @Bean 
    public UserDetailsService userDetailsService(){
        return new UserService();
    }
    
} //FIN DE LA CLASE 
