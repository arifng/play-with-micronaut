package com.arifng.controller;

import com.arifng.model.Book;
import com.arifng.repository.BookRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Produces;

@Controller("/books")
public class BookController {
    private final BookRepository bookRepository;

    BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    HttpResponse<Book> findById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }
}
