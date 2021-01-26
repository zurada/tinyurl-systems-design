package com.example.urlkeymanager.redis;


import com.example.urlkeymanager.model.UrlEntry;
import com.example.urlkeymanager.mongo.UrlEntryRepository;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlHashService {


    private final RedisTemplate<String, Object> redisTemplate;
    private final ValueOperations<String, Object> valueOperations;
    private final UrlEntryRepository urlEntryRepository;

    public UrlHashService(RedisTemplate<String, Object> redisTemplate, UrlEntryRepository urlEntryRepository) {
        this.redisTemplate = redisTemplate;
        valueOperations = redisTemplate.opsForValue();
        this.urlEntryRepository = urlEntryRepository;
    }

    public Long countAvailableHashCount() {
        return redisTemplate.execute(RedisServerCommands::dbSize);
    }

    public String pollRandomHash() {
        if (countAvailableHashCount() < 50) {
            throw new IllegalStateException("Not enough keys");
        }
        String key = redisTemplate.randomKey();
        while (key == null || key.startsWith("#")) {
            key = redisTemplate.randomKey();
        }
        Boolean wasDeleted = redisTemplate.delete(key);
        if (wasDeleted != null && !wasDeleted) {
            throw new IllegalArgumentException("The key has already been removed");
        }
        return key;
    }

    public void insertHash(String hash) {
        if (valueOperations.get(hash) != null) {
            throw new IllegalArgumentException("The key has already been added");
        }
        valueOperations.set(hash, hash);
    }

    public void createEntry(UrlEntry urlEntry) {
        urlEntryRepository.save(urlEntry);
    }

    public void removeEntry(String key) {
        urlEntryRepository.deleteById(key);
    }

    public Optional<UrlEntry> getEntry(String key) {
        return urlEntryRepository.findById(key);
    }
}
