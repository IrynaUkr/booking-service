package com.epam.cinema.service;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.User;
import com.epam.cinema.model.impl.TicketModel;

import java.util.List;

public interface TicketService extends CrudService<TicketModel> {
    TicketModel bookTicket(long userId, long eventId, int place);

    List<TicketModel> getBookedTickets(User user, int pageSize, int pageNum);

    List<TicketModel> getBookedTickets(Event event, int pageSize, int pageNum);

    boolean cancelTicket(long ticketId);
}
