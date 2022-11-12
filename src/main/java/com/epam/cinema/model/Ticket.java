package com.epam.cinema.model;

import com.epam.cinema.model.impl.Category;

/**
 * Class Ticket is an entity that describes cinema ticket for booking service.
 */
public interface Ticket extends Entity {

    Category getCategory();

   void setCategory(Category category);

    int getPlace();

    void setPlace(int place);
}
