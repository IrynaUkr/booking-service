package com.epam.cinema.model.impl;

import com.epam.cinema.model.Event;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventModel implements Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private LocalDate date;

    private Double ticketPrice;

    /**
     * one-to-many mapping means that one row in a table Event is mapped to multiple rows in table tickets.
     * Ticket could have only one event, but one event could be written in different tickets
     * reference to Event is added in each Ticket using @ManyToOne, making this a bidirectional relationship.
     * Bidirectional means that we are able to access tickets from events, and also events from tickets.
     */
    @OneToMany(mappedBy="event", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<TicketModel> tickets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventModel that = (EventModel) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(date, that.date) && Objects.equals(ticketPrice, that.ticketPrice) && Objects.equals(tickets, that.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, ticketPrice, tickets);
    }
}
