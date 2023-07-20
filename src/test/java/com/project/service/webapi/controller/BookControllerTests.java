package com.project.service.webapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.service.webapi.controllers.BooksController;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Applying test in the Controller: /api/v1/books/*")
public class BookControllerTests {

    @Autowired
    BooksController booksController;

    @Test
    @DisplayName("Testing if Application Loads Correctly")
    public void testContextLoadController() {
        assertThat(booksController).isNotNull();
    }

}
