package com.epam.cinema.model.impl;

import com.epam.cinema.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String email;

    private String name;

    /**
     * one-to-many mapping means that one row in a table User is mapped to multiple rows in table tickets.
     * Ticket could have only one user, but one user could have many tickets
     * reference to User is added in each Ticket using @ManyToOne, making this a bidirectional relationship.
     * Bidirectional means that we are able to access tickets from users, and also users from tickets.
     */
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<TicketModel> tickets;

    @OneToOne(mappedBy = "user")
    private UserAccount account;
}
