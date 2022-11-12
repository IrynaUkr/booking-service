package com.epam.cinema.service;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.impl.EventModel;

import java.time.LocalDate;
import java.util.List;

public interface EventService extends CrudService<EventModel> {
    List<EventModel> getEventsByTitle(String title, int pageSize, int pageNum);

    List<EventModel> getEventsForDay(LocalDate day, int pageSize, int pageNum);
}
