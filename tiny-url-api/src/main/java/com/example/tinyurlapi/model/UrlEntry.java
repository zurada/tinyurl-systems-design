package com.example.tinyurlapi.model;

import lombok.Data;

import java.time.Instant;

@Data
public class UrlEntry {
    String key;
    String url;
    Instant expireDate;

    public UrlEntry(String key, String url) {
        this.key = key;
        this.url = url;
        expireDate = Instant.now().plusSeconds(604800);
    }

    public UrlEntry() {
    }
}
