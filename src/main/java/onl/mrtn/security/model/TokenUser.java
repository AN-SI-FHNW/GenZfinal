/*
 * Authors: Kevin Pini, Moana Kleiner, Carla Kaufmann, Andrea Alex Simonek
 * Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package onl.mrtn.security.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.userdetails.User;
import static java.util.Collections.emptyList;

public class TokenUser extends User {
	private static final long serialVersionUID = 8703962942411979021L;
	private String email;
    private String remember;

    @JsonCreator
    public TokenUser(@JsonProperty(value = "email", required = true) String email, @JsonProperty(value = "password", required = true) String password, @JsonProperty(value = "remember", required = true) String remember){
        super(email, password, emptyList());
        this.email = email;
        this.remember = remember;
    }

    public String getEmail() {
        return email;
    }

    public String getRemember() {
        return remember;
    }

}
