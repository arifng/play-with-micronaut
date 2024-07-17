package com.arifng.controller;

import com.arifng.model.Book;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.Sql;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Sql(value = {"classpath:schema.sql", "classpath:data.sql"})
@MicronautTest
public class BookControllerTest {
    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    void shouldReturnABookWhenDataIsSaved() {
        BlockingHttpClient client = httpClient.toBlocking();
        HttpResponse<String> response = client.exchange("/books/1", String.class);
        assertEquals(HttpStatus.OK.getCode(), response.getStatus().getCode());

        DocumentContext context = JsonPath.parse(response.body());
        Long id = context.read("$.id", Long.class);
        assertEquals(1L, id);

        String title = context.read("$.title", String.class);
        assertEquals("Play with Micronaut", title);

        Integer price = context.read("$.price", Integer.class);
        assertEquals(200, price);
    }

    @Test
    void shouldNotReturnABookWithAnUnknownId() {
        BlockingHttpClient client = httpClient.toBlocking();
        HttpClientResponseException exception = assertThrows(HttpClientResponseException.class,
                () -> client.exchange("/books/2", String.class));
        assertEquals(HttpStatus.NOT_FOUND.getCode(), exception.getStatus().getCode());
    }
}
