package com.epam.cinema.controller;

import com.epam.cinema.controller.dto.TicketDto;
import com.epam.cinema.model.impl.EventModel;
import com.epam.cinema.model.impl.TicketModel;
import com.epam.cinema.model.impl.UserModel;
import com.epam.cinema.service.impl.BookingFacadeImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TicketController {
    private final BookingFacadeImpl bookingFacade;

    @PostMapping("/ticket")
    @ResponseStatus(HttpStatus.CREATED)
    public String bookTicket(@RequestBody TicketDto ticketDto, Model model){
        log.info("booking tickets was called in controller");
        TicketModel ticket = bookingFacade.bookTicket(ticketDto.getUserId(), ticketDto.getEventId(), ticketDto.getPlace());
        model.addAttribute("ticket", ticket);
        log.info("booking tickets was finished in controller");
        return "bookedTicket";
    }

    @GetMapping("/ticketsByUser")
    public String getBookedTicketsByUser(@RequestBody UserModel userModel, Model model){
        log.info("get booked tickets by User was called in controller");
        List<TicketModel> tickets = bookingFacade.getBookedTickets(userModel, 1, 1);
        model.addAttribute("tickets",tickets);
        log.info(" booked tickets by User was finished in controller");
         return "tickets";
    }
    @GetMapping("/ticketsByEvent")
    public String getBookedTicketsByEvent(@RequestBody EventModel event, Model model){
        log.info("get booked tickets by User was called in controller");
        List<TicketModel> tickets = bookingFacade.getBookedTickets(event, 1, 1);
        model.addAttribute("tickets",tickets);
        log.info(" booked tickets by Event {} was finished in controller", event);
        return "tickets";
    }

    @DeleteMapping("/ticket/{id}")
    public String cancelTicketById(@PathVariable("id") long id, Model model){
        log.info("cancel ticket by id was called in controller");
        boolean isDeleted = bookingFacade.cancelTicket(id);
        model.addAttribute("id", id);
        model.addAttribute("status", isDeleted);
        log.info(" cancel ticket by id {} was finished in controller", id);
         return "deletedTicket";
    }
}
