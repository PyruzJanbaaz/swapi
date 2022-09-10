package com.pyruz.dzm.swapi.controller;

import com.pyruz.dzm.swapi.service.PeopleService;
import com.pyruz.dzm.swapi.utility.ApplicationMessages;
import com.pyruz.dzm.swapi.utility.ApplicationProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(PeopleController.class)
public class PeopleControllerTest {

    @Autowired(required = false)
    private MockMvc mockMvc;

    @MockBean
    private PeopleService peopleService;

    @MockBean
    private ApplicationProperties applicationProperties;

    @MockBean
    ApplicationMessages applicationMessages;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(
                new PeopleController(peopleService),
                applicationProperties,
                applicationMessages
        ).build();
    }

    @Test
    public void shouldReturnPeople() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/people")).andExpect(status().isOk());
    }
}
