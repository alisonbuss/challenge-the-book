package com.project.service.webapi.controller;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.project.service.webapi.controllers.HelloWorldController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Applying test in the Controller: /api/v1/helloworld/*")
public class HelloWorldControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    HelloWorldController helloWorldController;

    @Test
    @DisplayName("Testing if Application Loads Correctly")
    public void testContextLoadController() {
        assertThat(helloWorldController).isNotNull();
    }

    @Test
    @DisplayName("GET /welcome")
    public void testGetWelcomeSuccess() throws Exception {
        // Execute the GET request
        mockMvc.perform(get("/api/v1/helloworld/welcome"))
            // Validate the response code and content type
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))

            // Validate headers
            .andExpect(header().string(HttpHeaders.LOCATION, "/welcome"))

            // Validate the returned fields
            .andExpect(jsonPath("$.message", is("Environment variable not found.")));
    }

    @Test
    @DisplayName("GET /welcome/{message}")
    public void testGetCustomWelcomeSuccess() throws Exception {
        // Execute the GET request
        mockMvc.perform(get("/api/v1/helloworld/welcome/{message}", "HelloWorld"))
            // Validate the response code and content type
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))

            // Validate headers
            .andExpect(header().string(HttpHeaders.LOCATION, "/welcome/HelloWorld"))
            .andExpect(header().string(HttpHeaders.ETAG, "\"HelloWorld\""))

            // Validate the returned fields
            .andExpect(jsonPath("$.message", is("HelloWorld")));
    }

    @Test
    @DisplayName("GET /welcome - Not Found")
    public void testGetWelcomeNotFound() throws Exception {
        // Execute the GET request
        mockMvc.perform(get("/api/v1/helloworld/welcome-notfound"))
            // Validate the response code
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /welcome - Error")
    public void testGetWelcomeError() throws Exception {
        // Execute the GET request
        mockMvc.perform(get("/api/v1/helloworld/welcome-error"))
            // Validate the response code
            .andExpect(status().isInternalServerError());
    }

}
