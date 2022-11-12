package com.epam.cinema.repository;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.Ticket;
import com.epam.cinema.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.epam.cinema.repository.utils.Utils.*;


@Slf4j
@Getter
@Setter
@Component
public class Storage {
    Map<String, Map<String, String>> commonStorageMap;

    public List<User> extractUsers() {
        log.info("extracting userList from storage");
        List<User> userList = new ArrayList<>();
        Stream<Map.Entry<String, Map<String, String>>> users = getFilteredListByEntity(commonStorageMap, "user");

        users.forEach(user -> {
            Map<String, String> value = user.getValue();
            User currentUser = createUser(value);
            log.info("user {} was extracted", currentUser);
            userList.add(currentUser);
        });
        return userList;
    }

    public List<Event> extractEvents() {
        log.info("extracting EventList from storage");
        List<Event> eventList = new ArrayList<>();
        Stream<Map.Entry<String, Map<String, String>>> events = getFilteredListByEntity(commonStorageMap, "event");

        events.forEach(event -> {
            Map<String, String> value = event.getValue();
            Event currentEvent = createEvent(value);
            log.info("event {} was extracted", currentEvent);
            eventList.add(currentEvent);
        });
        return eventList;
    }

    public List<Ticket> extractTickets() {
        log.info("extracting TicketList from storage");
        Stream<Map.Entry<String, Map<String, String>>> tickets = getFilteredListByEntity(commonStorageMap, "ticket");

        List<Ticket> ticketList = new ArrayList<>();
        tickets.forEach(ticket -> {
            Ticket currentTicket = createTicket(ticket.getValue());
            log.info("event {} was extracted", currentTicket);
            ticketList.add(currentTicket);
        });
        return ticketList;
    }
}
