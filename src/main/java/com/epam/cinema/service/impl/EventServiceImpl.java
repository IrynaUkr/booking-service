package com.epam.cinema.service.impl;

import com.epam.cinema.exception.EventAlreadyExistException;
import com.epam.cinema.exception.EventNotFoundException;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.impl.EventModel;
import com.epam.cinema.repository.EventRepository;
import com.epam.cinema.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public EventModel getById(long id) {
        log.info("finding event By Id {} ", id);
        EventModel persistedEvent = eventRepository
                .findById(id)
                .orElseThrow(()-> new EventNotFoundException("event was not found, id "+id));
        log.info("event with id {} was found", id);
        return persistedEvent;
    }

    @Override
    @Transactional
    public EventModel create(EventModel eventDto) {
        log.info("eventService method createEvent {} was called", eventDto);
        if (eventRepository.existsById(eventDto.getId())) {
            throw new EventAlreadyExistException("Event with id {} is already exist" + eventDto.getId());
        }

        EventModel savedEvent = eventRepository.save(eventDto);
        log.info("event was created");
        return savedEvent;
    }

    @Override
    @Transactional
    public EventModel updateById(long id, EventModel eventDto) {
        log.info("eventService method updateById {} was called", id);
        EventModel persistedOldEvent =getById(id);
        EventModel updatedEvent = mapPersistedEventFieldsToEventDtoFields(persistedOldEvent, eventDto);
        return eventRepository.save(updatedEvent);
    }

    private EventModel mapPersistedEventFieldsToEventDtoFields(EventModel persistedEvent, Event eventNewDto) {
        String title = eventNewDto.getTitle();
        if (Objects.nonNull(title)) {
            persistedEvent.setTitle(title);
        }
        LocalDate date = eventNewDto.getDate();
        if (Objects.nonNull(date)) {
            persistedEvent.setDate(date);
        }
        return persistedEvent;
    }

    @Override
    @Transactional
    public boolean deleteById(long id) {
        log.info("eventService method deleteById {} was called", id);
        eventRepository.deleteById(id);
        if (eventRepository.existsById(id)) {
            log.info("event  with id {} was not deleted", id);
            return false;
        } else {
            log.info("event with id {} was deleted", id);
            return true;
        }
    }

    @Override
    public List<EventModel> getEventsByTitle(String title, int pageSize, int pageNum) {
        log.info("eventService method geEventByTitle  {} was called", title);
        return eventRepository.findEventModelByTitle(title);
    }

    @Override
    public List<EventModel> getEventsForDay(LocalDate day, int pageSize, int pageNum) {
        log.info("eventService method getForDay {} was called", day);
        return eventRepository.findEventModelByDate(day);
    }
}
