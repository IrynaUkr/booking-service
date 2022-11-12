package com.epam.cinema.repository;

import com.epam.cinema.model.impl.EventModel;
import com.epam.cinema.model.impl.TicketModel;
import com.epam.cinema.model.impl.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TicketRepository extends JpaRepository<TicketModel, Long> {

    List<TicketModel> findTicketModelByUser(UserModel userModel);

    List<TicketModel> findTicketModelByEvent(EventModel eventModel);
}
