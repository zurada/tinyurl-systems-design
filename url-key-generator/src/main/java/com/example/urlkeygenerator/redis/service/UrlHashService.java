package com.example.urlkeygenerator.redis.service;

import com.example.urlkeygenerator.model.GenerationState;
import com.example.urlkeygenerator.model.Segment;
import com.example.urlkeygenerator.model.SegmentKeys;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class UrlHashService {


    private final String URL_HASH_CACHE = "URLHASH";
    private final String LAST_PERMUTATION_CACHE = "LASTPERMUTATION";
    private final String GENERATION_STATE = "GENERATION_STATE";
    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, String, String> hashOperations;
    private final HashOperations<String, String, Segment> hashSegmentOperations;
    private final HashOperations<String, String, GenerationState> hashStateOperation;

    public UrlHashService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
        hashSegmentOperations = redisTemplate.opsForHash();
        hashStateOperation = redisTemplate.opsForHash();
    }

    public void save(final SegmentKeys segmentKeys) {
        hashOperations.putAll(URL_HASH_CACHE, segmentKeys.getCreatedKeys()
                .stream()
                .collect(Collectors.toMap((s) -> s, (s) -> s)));
        saveLastSegment(segmentKeys.getEndingSegment());
    }

    public void saveLastSegment(final Segment segment) {
        hashSegmentOperations.put(LAST_PERMUTATION_CACHE, LAST_PERMUTATION_CACHE, segment);
    }

    public Segment getLastSegment() {
        return hashSegmentOperations.get(LAST_PERMUTATION_CACHE, LAST_PERMUTATION_CACHE);
    }

    public void setState(GenerationState state){
        hashStateOperation.put(GENERATION_STATE, GENERATION_STATE, state);
    }

    public GenerationState getState(){
        return hashStateOperation.get(GENERATION_STATE, GENERATION_STATE);
    }
}
