package com.addvance.restapi;

import com.addvance.restapi.types.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.ws.rs.Consumes;
import java.util.Optional;


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
    public ResponseEntity<String> getBookAuthorByName(@PathVariable("name") String name) {
        String author = service.getBookAuthorFromBookName(name);

        if (author != null) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            LOG.info(" something not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/name", produces = "application/json")
    public ResponseEntity<String> getBookAuthorByNameAsParameter(@RequestParam("bookName") String name) {
        String author = service.getBookAuthorFromBookName(name);

        if (author != null) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            LOG.info(" something not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/addBook",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> addBookToLocalDataStore(@RequestBody Book book) {
        Optional<Book> bookObject = service.addBookToDataStoreSimplified(book);
        if (bookObject.isPresent()) {
            return new ResponseEntity<>(bookObject.get(), HttpStatus.CREATED);
        } else {
            LOG.info("ERROR adding book to the datastore");
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

}
