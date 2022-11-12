package com.epam.cinema.service.impl;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.User;
import com.epam.cinema.model.impl.EventModel;
import com.epam.cinema.model.impl.TicketModel;
import com.epam.cinema.model.impl.UserAccount;
import com.epam.cinema.model.impl.UserModel;
import com.epam.cinema.service.EventService;
import com.epam.cinema.service.UserAccountService;
import com.epam.cinema.service.facade.BookingFacade;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
@Service
public class BookingFacadeImpl implements BookingFacade {

    private final UserServiceImpl userService;

    private final EventService eventService;

    private final TicketServiceImpl ticketService;

    private final UserAccountService accountService;



    @Override
    public EventModel getEventById(long eventId) {
        log.info("in facade was called getEventById method");
        return eventService.getById(eventId);
    }

    @Override
    public List<EventModel> getEventsByTitle(String title, int pageSize, int pageNum) {
        log.info("in facade was called getEventByTitle method");
        return eventService.getEventsByTitle(title,pageSize,pageNum);
    }

    @Override
    public List<EventModel> getEventsForDay(LocalDate day, int pageSize, int pageNum) {
        log.info("in facade was called getEventForDay method");
        return eventService.getEventsForDay(day,pageSize, pageNum);
    }

    @Override
    public EventModel createEvent(EventModel event) {
        log.info("in facade was called createEvent method");
        return eventService.create(event);
    }

    @Override
    public EventModel updateEvent(EventModel event) {
        log.info("in facade was called createEvent method");
        return eventService.create(event);
    }


    @Override
    public boolean deleteEvent(long eventId) {
        log.info("in facade was called deleteEvent method");
        return eventService.deleteById(eventId);
    }

    @Override
    public User getUserById(long userId) {
        log.info("in facade was called getUserById method");
        return userService.getById(userId);
    }

    @Override
    public UserModel getUserByEmail(String email) {
        log.info("in facade was called getUserByEmail method");
        return userService.getUserByEmail(email);
    }

    @Override
    public List<UserModel> getUsersByName(String name, int pageSize, int pageNum) {
        log.info("in facade was called getUserByName method");
        return userService.getUsersByName(name, pageSize,pageNum);
    }

    @Override
    public UserModel createUser(UserModel user) {
        log.info("in facade was called createUser method");
        return userService.create(user);
    }

    @Override
    public UserModel updateUser(long id,UserModel user) {
        log.info("in facade was called updateUser method");
        return userService.updateById(id, user);
    }

    @Override
    public boolean deleteUser(long userId) {
        log.info("in facade was called deleteByIdUser method");
        return userService.deleteById(userId);
    }

    @Override
    public TicketModel bookTicket(long userId, long eventId, int place) {
        log.info("in facade was called bookTicket method");
        return ticketService.bookTicket(userId,eventId, place);
    }

    @Override
    public List<TicketModel> getBookedTickets(User user, int pageSize, int pageNum) {
        log.info("in facade was called getBookedTicket  byUser method");
        return ticketService.getBookedTickets(user, pageSize, pageNum);
    }

    @Override
    public List<TicketModel> getBookedTickets(Event event, int pageSize, int pageNum) {
        log.info("in facade was called bookTicket by Event method");
        return ticketService.getBookedTickets(event,pageSize,pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        log.info("in facade was called cancelTicket method");
        return ticketService.cancelTicket(ticketId);
    }

    public Double refillAccountBalance(UserAccount account, Double amount){
        log.info("in facade was called refile account method");
        return accountService.refillAccount(account,amount);
    }

    public UserAccount createUserAccount(UserAccount account){
        return accountService.create(account);
    }
}
