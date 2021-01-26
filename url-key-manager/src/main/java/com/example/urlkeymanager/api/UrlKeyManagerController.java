package com.example.urlkeymanager.api;

import com.example.urlkeymanager.model.RandomHash;
import com.example.urlkeymanager.model.UrlEntry;
import com.example.urlkeymanager.redis.UrlHashService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class UrlKeyManagerController {

    private final UrlHashService urlHashService;

    public UrlKeyManagerController(UrlHashService urlHashService) {
        this.urlHashService = urlHashService;
    }

    @GetMapping("/countAvailableHashes")
    public Long countAvailableHashes() {
        return urlHashService.countAvailableHashCount();
    }

    @GetMapping("/pollRandomHash")
    public RandomHash pollRandomHash() {
        String hash = urlHashService.pollRandomHash();
        return new RandomHash(hash);
    }

    @PostMapping("/insert/{key}")
    public void insertHash(@PathVariable String key) {
        urlHashService.insertHash(key);
    }

    @PostMapping("/create")
    public void createEntry(@RequestBody UrlEntry entry) {
        urlHashService.createEntry(entry);
    }

    @PostMapping("/remove/{key}")
    public void removeEntry(@PathVariable String key) {
        urlHashService.removeEntry(key);
    }

    @GetMapping("/get/{key}")
    public Optional<UrlEntry> getEntry(@PathVariable String key) {
        return urlHashService.getEntry(key);
    }


}
