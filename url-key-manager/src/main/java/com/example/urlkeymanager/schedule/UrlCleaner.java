package com.example.urlkeymanager.schedule;

import com.example.urlkeymanager.mongo.UrlEntryRepository;
import com.example.urlkeymanager.redis.UrlHashService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UrlCleaner {

    private final UrlHashService urlHashService;
    private final UrlEntryRepository urlEntryRepository;

    public UrlCleaner(UrlHashService urlHashService, UrlEntryRepository urlEntryRepository) {
        this.urlHashService = urlHashService;
        this.urlEntryRepository = urlEntryRepository;
    }

    @Scheduled(fixedRate = 3600000)
    public void scheduleFixedDelayTask() {
        urlEntryRepository.findByExpireDateBefore(Instant.now().minusSeconds(10))
                .forEach(urlEntry -> {
                    urlHashService.removeEntry(urlEntry.getKey());
                    urlHashService.insertHash(urlEntry.getKey());
                });

    }
}
