package ru.edu.springweb.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import ru.edu.springweb.config.WebConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitConfig(WebConfig.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class BookControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Test
    public void testAddBook() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mockMvc.perform(post("/books")
                .contentType("application/json")
                .content("{\"title\": \"Book 1\", \"author\": \"Author 1\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAllBook() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetBookByIdNotFound() throws Exception{
        MockMvc mockMvc =
        MockMvcBuilders.webAppContextSetup(context).build();
        mockMvc.perform(get("/books/999"))
                .andExpect(status().isNotFound());
    }

}
