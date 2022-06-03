/*
 * Authors: Kevin Pini, Moana Kleiner, Carla Kaufmann, Andrea Alex Simonek
 * Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package onl.mrtn.security.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Token {

    @Id
    private String token;

    public Token() {}

    public Token(String token) {
        this.token = token;
    }
}
