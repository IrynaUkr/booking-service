package com.epam.cinema.model.impl;

import com.epam.cinema.model.Ticket;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketModel implements Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int place;

    @Enumerated(EnumType.STRING)
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    /**
     * ManyToOne annotation is associated Ticket with the UserModel class variable.
     * JoinColumn annotation references the mapped column.
     */
    @ManyToOne
    @JoinColumn(name="user_model_id",referencedColumnName = "id" )
    private UserModel user;

    /**
     * ManyToOne annotation is associated Ticket with the EventModel class variable.
     * JoinColumn annotation references the mapped column.
     */
    @ManyToOne
    @JoinColumn(name="event_model_id",referencedColumnName = "id" )
    private EventModel event;
}
