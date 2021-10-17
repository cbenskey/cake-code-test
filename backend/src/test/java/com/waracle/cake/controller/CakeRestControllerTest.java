package com.waracle.cake.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cake.request.CakeRequest;

@SpringBootTest
@AutoConfigureMockMvc
class CakeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void should_saveCake_whenValid() throws Exception {
        final CakeRequest request = new CakeRequest();
        request.setTitle("New Cake");
        request.setDesc("A new test cake");
        request.setImage("http://testimgages.jpg");

        final ObjectMapper mapper = new ObjectMapper();
        final String payload = mapper.writeValueAsString(request);

        this.mockMvc.perform(post("/cakes").contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isOk())
                .andExpect(content()
                .string(containsString("New Cake")));

        this.mockMvc.perform(get("/cakes")).andExpect(status().isOk())
                .andExpect(content().string(containsString("New Cake")));
    }

    @Test
    public void should_loadAllCakes_default() throws Exception {
        this.mockMvc.perform(get("/cakes")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Birthday cake")));
    }
}