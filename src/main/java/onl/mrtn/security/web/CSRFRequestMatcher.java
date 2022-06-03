/*
 * Authors: Kevin Pini, Moana Kleiner, Carla Kaufmann, Andrea Alex Simonek
 * Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package onl.mrtn.security.web;

import org.springframework.security.web.util.matcher.RequestMatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;

public class CSRFRequestMatcher implements RequestMatcher {

    private final HashSet<String> allowedMethods = new HashSet<>(Arrays.asList("GET", "HEAD", "TRACE", "OPTIONS"));

    @Override
    public boolean matches(HttpServletRequest request) {
        if(this.allowedMethods.contains(request.getMethod())||request.getCookies()==null){
            return false;
        }
        return true;
    }
}
