package com.arifng.controller;

import com.arifng.Book;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

@Controller("/books")
public class BookController {
    @Get("/{id}")
    Book findById(@PathVariable Long id) {
        if (id.equals(1L)) {
            return new Book(1L, "Micronaut in Action", 200);
        }
        return null;
    }
}
