package com.epam.cinema.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static com.epam.cinema.constants.TestData.*;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j

@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;



    @Test
    public void isServletContextNotNull() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
    }


    @Test
    void getEventById() throws Exception {
        this.mockMvc
                .perform(get("/event/{id}", EVENT_ID_CHRISTMAS))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(EVENT_DATE_CHRISTMAS)))
                .andExpect(content().string(containsString(EVENT_TITLE_CHRISTMAS)));
    }

    @Test
    void getAllEventsByTitle() throws Exception {
        this.mockMvc
                .perform(get("/event/{title}/events", EVENT_TITLE_CHRISTMAS))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(EVENT_DATE_CHRISTMAS)))
                .andExpect(content().string(containsString(EVENT_TITLE_CHRISTMAS)));
    }

    @Test
    void getAllEventsByDay() throws Exception {
        this.mockMvc
                .perform(get("/event/{day}/eventsForDay", EVENT_DATE_CHRISTMAS))
                .andDo(print()).andExpect(status().isOk());
           //     .andExpect(content().string(containsString(EVENT_TITLE_CHRISTMAS)));
    }

    @Test
    void showFormCreatingEvent() throws Exception {
        this.mockMvc
                .perform(get("/new"))
                .andExpect(view().name("createNewEvent"))
                .andExpect(model().attributeExists("event"))
                .andExpect(status().isOk());
    }

    @Test
    void createEvent() throws Exception {
        MockHttpServletRequestBuilder createMessage = post("/eventCreate")
                .param("title", EVENT_NEW_TITLE);

        this.mockMvc
                .perform(createMessage)
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string(containsString("the event was created")))
                .andExpect(content().string(containsString(EVENT_NEW_TITLE)));
    }

    @Test
    void deleteEventById() throws Exception {
        this.mockMvc
                .perform(delete("/event/{id}", EXISTED_EVENT_ID))
                .andExpect(view().name("deleteEvent"))
                .andExpect(status().isOk());
    }
}