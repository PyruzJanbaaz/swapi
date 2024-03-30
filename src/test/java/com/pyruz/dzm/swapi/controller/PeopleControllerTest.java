package com.pyruz.dzm.swapi.controller;

import com.pyruz.dzm.swapi.service.PeopleService;
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

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new PeopleController(peopleService)).build();
    }

    @Test
    public void shouldReturnPeople() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/people")).andExpect(status().isOk());
    }
}
