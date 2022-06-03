/*
 * Author: Andrea Alec Simonek, Kevin Pini, Carla Kaufmann, Moana Kleiner
 * Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package ch.fhnw.GenZ.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    // password encoder (BCryptPasswordEncoder())
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}