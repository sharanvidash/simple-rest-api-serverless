package com.addvance.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class RestApiService {

    private static final Logger LOG = LoggerFactory.getLogger(RestApiService.class);

    private static final Map<String, String> bookMap = Map.of("Recursion", "Blake");

    public String getBookAuthorFromBookName(String bookName) {
        return bookMap.getOrDefault(bookName, "");
    }

}
