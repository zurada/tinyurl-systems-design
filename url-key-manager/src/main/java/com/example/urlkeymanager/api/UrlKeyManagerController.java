package com.example.urlkeymanager.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UrlKeyManagerController {

    @GetMapping("/countAvailableHashes")
    public void countAvailableHashes() {
    }

    @GetMapping("/pollRandomHash")
    public void pollRandomHash() {
    }

    @PostMapping("/insertHash")
    public void insertHash(String hash) {
    }

    @PostMapping("/create")
    public void createEntry() {
    }

    @GetMapping("/get")
    public void getEntry() {
    }

}
