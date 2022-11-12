package com.epam.cinema.model;
/**
 * Class User is an entity that describes customer of booking service application.
 */
public interface User extends Entity{

    String getName();

    void setName(String name);

    /**
     * User email. UNIQUE.
     */
    String getEmail();

    void setEmail(String email);
}
