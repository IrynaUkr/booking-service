package com.epam.cinema.repository;

import com.epam.cinema.model.impl.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface EventRepository extends JpaRepository<EventModel, Long> {
     List<EventModel> findEventModelByTitle(String title);

     List<EventModel> findEventModelByDate(LocalDate day);

     @Override
     Optional<EventModel> findById(Long id);
}
