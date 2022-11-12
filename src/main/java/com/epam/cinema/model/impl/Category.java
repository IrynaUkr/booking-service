package com.epam.cinema.model.impl;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    private int id;

    private String category;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private Set<TicketModel>tickets;
}
