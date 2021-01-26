package com.example.urlkeymanager.model;

import lombok.Data;

@Data
public class RandomHash {
    private String key;

    public RandomHash(String s) {
        this.key = s;
    }
}
