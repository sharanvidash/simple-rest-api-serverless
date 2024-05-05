package com.addvance.restapi;

import com.addvance.restapi.types.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class RestApiService {

    private static final Logger LOG = LoggerFactory.getLogger(RestApiService.class);

    private static final Map<String, String> bookMap = Map.of("Recursion", "Blake");
    private final ObjectMapper objectMapper;

    private final Map<String, Book> bookDataStore;

    RestApiService(@Qualifier("objectMapper") ObjectMapper objectMapperImpl) {
        bookDataStore = new HashMap<>();
        objectMapper = objectMapperImpl;
    }

    public String getBookAuthorFromBookName(String bookName) {
        return bookMap.getOrDefault(bookName, "");
    }

    public Book getBookFromName(String bookName) {
        return bookDataStore.getOrDefault(bookName, new Book());
    }

    public Book addBookToDataStore(String book) {
        try {
            Book convertedBook = objectMapper.readValue(book, Book.class);
            bookDataStore.put(convertedBook.getTitle(), convertedBook);
            return convertedBook;
        } catch (Exception e) {
            LOG.error("Book object conversion hit an exception ", e);
        }
        return new Book();
    }

    public Optional<Book> addBookToDataStoreSimplified(Book book) {
        try {
            bookDataStore.put(book.getTitle(), book);
            return Optional.of(book);
        } catch (Exception e) {
            LOG.error("Book object conversion hit an exception ", e);
        }
        return Optional.empty();
    }

}
