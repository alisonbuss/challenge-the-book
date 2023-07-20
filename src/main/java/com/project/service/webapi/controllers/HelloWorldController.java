package com.project.service.webapi.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/v1/helloworld")
@CrossOrigin(origins = "*", maxAge = 4800)
public class HelloWorldController {

    private Logger logger = LogManager.getLogger(HelloWorldController.class);

    @Value("${APP_HELLO_WORLD:Environment variable not found.}")
    private String welcomeMessage;

    public HelloWorldController() {
        logger.trace("TRACE");
        logger.debug("DEBUG");
        logger.info("INFO");
        logger.warn("WARN");
        logger.error("ERROR");
        logger.fatal("FATAL");
    }

    @GetMapping(path = "/welcome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, String>> welcome() {
        try {
            var response = new HashMap<String, String>();
            response.put("message", welcomeMessage);

            logger.info("INFO - GET /welcome");

            return ResponseEntity
                .status(HttpStatus.OK)
                .location(new URI("/welcome"))
                .body(response);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/welcome/{message}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, String>> welcomeCustom(
        @PathVariable(value = "message") String message
    ) {
        try {
            var response = new HashMap<String, String>();
            response.put("message", message);

            logger.info("INFO - GET /welcome{message}");

            return ResponseEntity
                .status(HttpStatus.OK)
                .location(new URI("/welcome/".concat(message)))
                .eTag(message)
                .body(response);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/welcome-notfound", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, String>> welcomeNotFound() {
        logger.warn("WARN - GET /welcome-notfound");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping(path = "/welcome-error", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, String>> welcomeError() {
        logger.error("ERROR - GET /welcome-error");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
