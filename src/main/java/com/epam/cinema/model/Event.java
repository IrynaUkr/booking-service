package com.epam.cinema.model;

import java.time.LocalDate;

/**
 * Class Event is an entity that describes event that is in the ticket for booking service.
 */
public interface Event extends Entity {

    String getTitle();

    void setTitle(String title);

    LocalDate getDate();

    void setDate(LocalDate date);
}
