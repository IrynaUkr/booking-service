package com.epam.cinema.service.impl;

import com.epam.cinema.exception.EventAlreadyExistException;
import com.epam.cinema.exception.EventNotFoundException;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.impl.EventModel;
import com.epam.cinema.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    public static final String EVENT_TEST_TITLE = "event test title";

    public static final long EVENT_TEST_ID = 1L;

    public static final String EVENT_TEST_DATE = "2022-01-01";

    EventModel testEvent;

    List<EventModel> testEventList;

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventRepository eventRepository;

    @BeforeEach
    void setUp() {
        testEvent = new EventModel();
        testEvent.setDate(LocalDate.parse(EVENT_TEST_DATE));
        testEvent.setTitle(EVENT_TEST_TITLE);
        testEvent.setId(EVENT_TEST_ID);

        testEventList = new ArrayList<>();
        testEventList.add(testEvent);
    }


    @Test
    void shouldReturnValidEventWhenEventExistTest() {
        when(eventRepository.findById(EVENT_TEST_ID)).thenReturn(Optional.of(testEvent));

        EventModel actualEvent = eventService.getById(EVENT_TEST_ID);
        assertThat(testEvent, equalTo(actualEvent));
    }

    @Test
    void shouldThrowEventNotFoundExceptionIfEventIsNotPresentsTest(){
        when(eventRepository.findById(EVENT_TEST_ID)).thenReturn(Optional.empty());
        assertThrows(EventNotFoundException.class,
                ()-> eventService.getById(EVENT_TEST_ID));
    }


    @Test
    void shouldCreateValidEventTest() {
        when(eventRepository.existsById(EVENT_TEST_ID)).thenReturn(false);
        when(eventRepository.save(testEvent)).thenReturn(testEvent);

        EventModel actualEvent = eventService.create(testEvent);

        assertThat(testEvent, equalTo(actualEvent));
    }

    @Test
    void shouldThrowEventAlreadyExistExceptionIfCreatingEventPresentsTest(){
        when(eventRepository.existsById(any())).thenReturn(true);

        assertThrows(EventAlreadyExistException.class,
                ()-> eventService.create(testEvent));
    }

    @Test
    void shouldBeUpdatedByIdTest() {

        when(eventRepository.findById(EVENT_TEST_ID)).thenReturn(Optional.of(testEvent));
        when(eventRepository.save(testEvent)).thenReturn(testEvent);

        EventModel actualEvent = eventService.updateById(EVENT_TEST_ID, testEvent);

        assertThat(actualEvent, samePropertyValuesAs(testEvent));
        assertThat(actualEvent, hasProperty("id",equalTo(EVENT_TEST_ID)));
    }

    @Test
    void deleteById() {
        eventService.deleteById(EVENT_TEST_ID);

        verify(eventRepository).deleteById(EVENT_TEST_ID);
    }

    @Test
    void getEventsByTitle() {
        when(eventRepository.findEventModelByTitle(EVENT_TEST_TITLE)).thenReturn(testEventList);

        eventService.getEventsByTitle(EVENT_TEST_TITLE,1,1);

        for(Event ev:testEventList){
            assertThat(ev,hasProperty("title",equalTo(EVENT_TEST_TITLE)));
        }
        assertThat(testEventList,hasSize(1));
    }

    @Test
    void getEventsForDay() {
        when(eventRepository.findEventModelByDate(LocalDate.parse(EVENT_TEST_DATE))).thenReturn(testEventList);

        eventService.getEventsForDay(LocalDate.parse(EVENT_TEST_DATE), 1,1);
        for(Event ev:testEventList){
            assertEquals(ev.getDate(), LocalDate.parse(EVENT_TEST_DATE));
        }
        assertThat(testEventList,hasSize(1));
    }
}
