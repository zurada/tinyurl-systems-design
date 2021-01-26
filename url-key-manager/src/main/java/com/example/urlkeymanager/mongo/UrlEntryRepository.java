package com.example.urlkeymanager.mongo;

import com.example.urlkeymanager.model.UrlEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface UrlEntryRepository extends MongoRepository<UrlEntry, String> {
        List<UrlEntry> findByExpireDateBefore(Instant instant);
}
