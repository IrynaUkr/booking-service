package com.epam.cinema.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TicketDto {
    private long userId;
    private long eventId;
    private int place;
}
