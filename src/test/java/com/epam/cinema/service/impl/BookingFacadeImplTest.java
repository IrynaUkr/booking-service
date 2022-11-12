package com.epam.cinema.service.impl;//package com.epam.cinema.service.impl;
//
//import com.epam.cinema.model.Event;
//import com.epam.cinema.model.impl.EventModel;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//class BookingFacadeImplTest {
//    public static final int EXISTED_EVENT_ID = 1123;
//    public static final int NEW_EVENT_ID = 999;
//    public static final String NEW_EVENT_DATE = "2022-02-02";
//    public static final String NEW_EVENT_TITLE = "new Event";
//    public static final int EVENT_ID = 1122;
//
//    BookingFacadeImpl bookingFacade;
//
//    ApplicationContext context;
//    Event expected;
//
//    @BeforeEach
//    void setUp(){
//        expected = new EventModel();
//        expected.setId(1122);
//        expected.setTitle("Christmas");
//        expected.setDate(LocalDate.parse("2022-10-31"));
//    }
//
//    @Test
//    void applicationContextShouldNotBeNullTest(){
//        assertNotNull(context);
//    }
//    @Test
//    void shouldCreateFacadeBeanWithPNameSpaceTest(){
//        BookingFacadeImpl bean = context.getBean(BookingFacadeImpl.class);
//
//        assertNotNull(bean);
//
//    }
//
//
//    @Test
//    void shouldReturnValidEventByIdIT(){
//        Event actualEvent = bookingFacade.getEventById(EVENT_ID);
//
//        assertEquals(expected.getId(), actualEvent.getId());
//        assertEquals(expected.getTitle(),actualEvent.getTitle());
//    }
//
////    @Test
////    void shouldCreateEventIT() {
////        Event expected = new EventModel();
////        expected.setId(NEW_EVENT_ID);
////        expected.setDate(LocalDate.parse(NEW_EVENT_DATE));
////        expected.setTitle(NEW_EVENT_TITLE);
////
////    //    Event actual = bookingFacade.createEvent(expected);
////
////        assertEquals(expected.getId(),actual.getId());
////        assertEquals(expected.getTitle(),actual.getTitle());
////        assertEquals(expected.getDate(),actual.getDate());
////    }
//
//    @Test
//    void shouldDeleteEventIT() {
//        boolean isDeleted = bookingFacade.deleteEvent(EXISTED_EVENT_ID);
//        assertTrue(isDeleted);
//    }
//}
