/*
 * Authors: Kevin Pini, Moana Kleiner, Carla Kaufmann, Andrea Alec Simonek
 * Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package onl.mrtn.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import onl.mrtn.security.service.TokenService;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({TokenSecurityConfiguration.class,
        TokenSecurityProperties.class,
        TokenService.class})
@Configuration
public @interface EnableTokenSecurity {
}
