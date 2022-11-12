package com.epam.cinema.repository.utils;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.Ticket;
import com.epam.cinema.model.User;
import com.epam.cinema.model.impl.EventModel;
import com.epam.cinema.model.impl.TicketModel;
import com.epam.cinema.model.impl.UserModel;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Stream;

public class Utils {

    private Utils() {
    }

    public static Stream<Map.Entry<String, Map<String, String>>> getFilteredListByEntity(Map<String, Map<String, String>> map, String entityTitle) {
        return map
                .entrySet()
                .stream()
                .filter(commonEntry -> commonEntry.getKey().contains(entityTitle));
    }

    public static User createUser(Map<String, String> value) {
        User user = new UserModel();
        if (isNumeric(value.get("id"))) {
            user.setId(Integer.parseInt(value.get("id")));
        }
        user.setEmail(value.get("email"));
        user.setName(value.get("name"));
        return user;
    }

    public static Event createEvent(Map<String, String> value) {
        Event currentEvent = new EventModel();
        if (isNumeric(value.get("id"))) {
            currentEvent.setId(Integer.parseInt(value.get("id")));
        }
        currentEvent.setTitle(value.get("title"));
        LocalDate dateTime = LocalDate.parse(value.get("date"));
        currentEvent.setDate(dateTime);
        return currentEvent;
    }

    public static Ticket createTicket(Map<String, String> value) {
        Ticket currentTicket = new TicketModel();
        if (isNumeric(value.get("id"))) {
            currentTicket.setId(Integer.parseInt(value.get("id")));
        }
//        if (isNumeric(value.get("eventId"))) {
//            currentTicket.setEventId(Integer.parseInt(value.get("eventId")));
//        }
//        if (isNumeric(value.get("userId"))) {
//            currentTicket.setUserId(Integer.parseInt(value.get("userId")));
//        }
        if (isNumeric(value.get("place"))) {
            currentTicket.setPlace(Integer.parseInt(value.get("place")));
        }
        return currentTicket;
    }

    public static boolean isNumeric(String value) {
        for (Character character : value.toCharArray()) {
            if (!Character.isDigit(character)) {
                throw new IllegalArgumentException("value is not a number"+ value);
            }
        }
        return true;
    }
}
