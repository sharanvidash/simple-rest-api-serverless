package com.addvance.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestController
@EnableWebMvc
@RequestMapping(value = "/apiv1")
@Import(RestApiService.class)
public class RestApiController {

    private final RestApiService service;

    private static final Logger LOG = LoggerFactory.getLogger(RestApiController.class);

    public RestApiController(RestApiService service) {
        this.service = service;
    }

    @GetMapping(value = "/{name}", produces = "application/json")
    public ResponseEntity<Object> getBookAuthorByName(@PathVariable("name") String name) {
        String author = service.getBookAuthorFromBookName(name);

        if (author != null) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            LOG.info(" something not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/name", produces = "application/json")
    public ResponseEntity<Object> getBookAuthorByNameAsParameter(@RequestParam("bookName") String name) {
        String author = service.getBookAuthorFromBookName(name);

        if (author != null) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            LOG.info(" something not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
