package com.epam.cinema.service.impl;

import com.epam.cinema.exception.BalanceNotEnoughMoneyException;
import com.epam.cinema.exception.EventNotFoundException;
import com.epam.cinema.exception.TicketNotFoundException;
import com.epam.cinema.exception.UserNotFoundException;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.User;
import com.epam.cinema.model.impl.EventModel;
import com.epam.cinema.model.impl.TicketModel;
import com.epam.cinema.model.impl.UserAccount;
import com.epam.cinema.model.impl.UserModel;
import com.epam.cinema.repository.EventRepository;
import com.epam.cinema.repository.TicketRepository;
import com.epam.cinema.repository.UserAccountRepository;
import com.epam.cinema.repository.UserRepository;
import com.epam.cinema.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private final UserAccountRepository accountRepository;

    private final EventRepository eventRepository;

    @Override
    public TicketModel getById(long id) {
        log.info("find ticket by id {}", id);
        TicketModel persistedTicket = ticketRepository
                .findById(id)
                .orElseThrow(() -> new TicketNotFoundException("ticket was not found, id:" + id));
        log.info("ticket was found, id {}", id);
        return persistedTicket;
    }

    @Override
    @Transactional
    public TicketModel create(TicketModel ticket) {
        log.info("creating ticket starts");

        UserModel userPersisted = userRepository
                .findById(ticket.getUser().getId())
                .orElseThrow(() -> new UserNotFoundException("user not found"));

        EventModel eventPersisted = eventRepository
                .findById(ticket.getId())
                .orElseThrow(() -> new EventNotFoundException("event not found"));
        ticket.setUser(userPersisted);
        ticket.setEvent(eventPersisted);
        TicketModel ticketPersisted = ticketRepository.save(ticket);
        log.info("creating ticket {} completed", ticketPersisted);
        return null;
    }

    @Override
    public TicketModel updateById(long id, TicketModel entityDto) {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    @Transactional
    public TicketModel bookTicket(long userId, long eventId, int place) {
        log.info("booking ticket starts with userId{}, eventId{},place{}", userId, eventId, place);
        UserModel userPersisted = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found,requested id:  " + userId));

        EventModel eventPersisted = eventRepository
                .findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("event not found, requested id:" + eventId));

        Double ticketPrice = eventPersisted.getTicketPrice();
        Double balance = userPersisted.getAccount().getBalance();

        if (ticketPrice *place > balance){
            throw new BalanceNotEnoughMoneyException("There is not enough money in your account");
        }else{
            UserAccount account = userPersisted.getAccount();
            account.setBalance(balance-ticketPrice*place);
            accountRepository.save(account);
        }
        TicketModel ticket = getTicketModel(place, userPersisted, eventPersisted);
        TicketModel ticketPersisted = ticketRepository.save(ticket);
        log.info("booking ticked completed, ticket {} was saved", ticketPersisted);

        return ticketPersisted;
    }

    private static TicketModel getTicketModel(int place, UserModel userPersisted, EventModel eventPersisted) {
        TicketModel ticket = new TicketModel();
        ticket.setEvent(eventPersisted);
        ticket.setUser(userPersisted);
        ticket.setPlace(place);
        return ticket;
    }

    @Override
    public List<TicketModel> getBookedTickets(User user, int pageSize, int pageNum) {
        log.info("finding booked tickets by user {} starts", user);
        UserModel userPersisted = userRepository
                .findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("user not found,requested id:  " + user.getId()));
        List<TicketModel> ticketModelByUser = ticketRepository.findTicketModelByUser(userPersisted);
        log.info("booked tickets by user {} were found", user);
        return ticketModelByUser;
    }

    @Override
    public List<TicketModel> getBookedTickets(Event event, int pageSize, int pageNum) {
        log.info("finding booked tickets by event {} starts", event);
        EventModel eventPersisted = eventRepository
                .findById(event.getId())
                .orElseThrow(() -> new EventNotFoundException("event was not found, id" + event.getId()));
        List<TicketModel> tickets = ticketRepository.findTicketModelByEvent(eventPersisted);
        log.info("booked tickets by event {} were found", event);
        return tickets;
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        ticketRepository.deleteById(ticketId);
        if (ticketRepository.existsById(ticketId)) {
            log.info("ticket was with id {} not deleted", ticketId);
            return false;
        } else {
            log.info("ticket with id {} was deleted", ticketId);
            return true;
        }
    }
}
