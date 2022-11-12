package com.epam.cinema.controller;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.impl.EventModel;
import com.epam.cinema.service.impl.BookingFacadeImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * EventController class delegates to BookingFacade methods.
 * Model interface defines a holder for model attributes, it was used to access data from request, and to access processed data in response.
 * Spring automatically wired data into the model from request
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class EventController {

    private final BookingFacadeImpl bookingFacade;

    /**
     * @param id  is unique event id, long type required
     * @param model is used to send the received data  from database to the view
     * @return the name of  event.html  view that renders the event
     */
    @GetMapping("/event/{id}")
    public String getEventById(@PathVariable("id") long id, Model model) {
        log.info("call get event by id {} in controller",id);
        Event eventById = bookingFacade.getEventById(id);
        model.addAttribute("event", eventById);

        return "event";
    }

    /**
     * @param title  is not unique, String type required
     * @param model is used to send the received data  from database to the view
     * @return the name of  events.html  view that renders the list of events
     */
    @GetMapping("/event/{title}/events")
    public String getAllEventsByTitle(@PathVariable("title") String title, Model model) {
        log.info("call get event by title {} in controller",title);
        List<EventModel> eventsByTitle = bookingFacade.getEventsByTitle(title, 1, 1);
        model.addAttribute("events", eventsByTitle);

        return "events";
    }

    /**
     * @param day  is date of event,  required YYYY-MM-DD for correct request
     * @param model is used to send the received data  from database to the view
     * @return the name of  events.html  view that renders the list events that were filtered by day
     */
    @GetMapping("/event/{day}/eventsForDay")
    public String getAllEventsByDay(@PathVariable("day") String day, Model model) {
        log.info("call get events by date {} in controller",day);
        List<EventModel> eventsByDay = bookingFacade.getEventsForDay(LocalDate.parse(day), 1, 1);
        model.addAttribute("events", eventsByDay);

        return "events";
    }

    /**
     * @param model is used to send the received data  from database to the view
     * @return the name of  createNewEvent.html  view that renders the form for creating event
     */
    @GetMapping("/new")
    public String showFormCreatingEvent(Model model){
        log.info("call method creating new form");
        EventModel event =new EventModel();
        model.addAttribute("event", event);

        return "createNewEvent";
    }

    /**
     * @param event is annotated with @ModelAttribute indicates to retrieve the argument from the model,
     * the arguments fields  populate from all request parameters that have matching names.
     * @param model is used to send the received data  from database to the view
     * @return the name of  createNewEvent.html  view that renders the form for creating event
     */
    @PostMapping("/eventCreate")
    @ResponseStatus(HttpStatus.CREATED)
    public String createEvent(@ModelAttribute("event") EventModel event, Model model){
        Event createdEvent = bookingFacade.createEvent(event);
        model.addAttribute("event", createdEvent);

        return "eventCreated";
    }

    /**
     * @param id  is unique event id, long type required
     * @param model is used to send the received data  from database to the view
     * @return the name of  delete.html  view that renders the deleted event
     */
    @DeleteMapping("/event/{id}")
    public String deleteEventById(@PathVariable("id")long id, Model model){
        log.info("call delete by id {} in controller",id);
        boolean isDeleted = bookingFacade.deleteEvent(id);
        model.addAttribute("id", id);
        model.addAttribute("status", isDeleted);

        return "deleteEvent";
    }
}
