package com.arifng;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Book(Long id, String title, Integer price) {
}
