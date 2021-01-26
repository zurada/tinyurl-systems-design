package com.example.urlkeymanager.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Data
public class UrlEntry {
    @Id
    String key;
    String url;
    Instant expireDate;

    @JsonCreator
    public UrlEntry(String key, String url){
        this.key = key;
        this.url = url;
        expireDate = Instant.now().plusSeconds(604800);
    }
}
