package com.fuelstation.vehicle_service_fuel_station.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .defaultSuccessUrl("/bookings", true) // redirect after login
                        .permitAll())
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}
