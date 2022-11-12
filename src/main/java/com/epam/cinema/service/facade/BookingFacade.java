package com.epam.cinema.service.facade;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.Ticket;
import com.epam.cinema.model.User;
import com.epam.cinema.model.impl.EventModel;
import com.epam.cinema.model.impl.TicketModel;
import com.epam.cinema.model.impl.UserModel;

import java.time.LocalDate;
import java.util.List;

/**
 * Groups together all operations related to tickets booking.
 */
public interface BookingFacade {

    EventModel getEventById(long eventId);

    List<EventModel> getEventsByTitle(String title, int pageSize, int pageNum);

    List<EventModel> getEventsForDay(LocalDate day, int pageSize, int pageNum);

    EventModel createEvent(EventModel event);

    EventModel updateEvent(EventModel event);

    boolean deleteEvent(long eventId);

    User getUserById(long userId);

    User getUserByEmail(String email);

    List<UserModel> getUsersByName(String name, int pageSize, int pageNum);

    UserModel createUser(UserModel user);

    UserModel updateUser(long id,UserModel user);

    boolean deleteUser(long userId);

    TicketModel bookTicket(long userId, long eventId, int place);

    /**
     * Get all booked tickets for specified user. Tickets should be sorted by event date in descending order.
     * @param user User
     * @param pageSize Pagination param. Number of tickets to return on a page.
     * @param pageNum Pagination param. Number of the page to return. Starts from 1.
     * @return List of Ticket objects.
     */
    List<TicketModel> getBookedTickets(User user, int pageSize, int pageNum);

    /**
     * Get all booked tickets for specified event. Tickets should be sorted in by user email in ascending order.
     * @param event Event
     * @param pageSize Pagination param. Number of tickets to return on a page.
     * @param pageNum Pagination param. Number of the page to return. Starts from 1.
     * @return List of Ticket objects.
     */
    List<TicketModel> getBookedTickets(Event event, int pageSize, int pageNum);

    /**
     * Cancel ticket with a specified id.
     * @param ticketId Ticket id.
     * @return Flag whether anything has been canceled.
     */
    boolean cancelTicket(long ticketId);

}
