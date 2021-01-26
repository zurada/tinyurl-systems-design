package com.example.urlkeygenerator.redis.service;

import com.example.urlkeygenerator.model.GenerationState;
import com.example.urlkeygenerator.model.Segment;
import com.example.urlkeygenerator.model.SegmentKeys;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class UrlHashService {


    private final String LAST_PERMUTATION_CACHE = "#LASTPERMUTATION";
    private final String GENERATION_STATE = "#GENERATION_STATE";
    private final RedisTemplate<String, Object> redisTemplate;
    private final ValueOperations<String, Object> valueOperations;
    private final HashOperations<String, String, Segment> hashSegmentOperations;
    private final HashOperations<String, String, GenerationState> hashStateOperation;

    public UrlHashService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        valueOperations = redisTemplate.opsForValue();
        hashSegmentOperations = redisTemplate.opsForHash();
        hashStateOperation = redisTemplate.opsForHash();
    }

    public void save(final SegmentKeys segmentKeys) {
        segmentKeys.getCreatedKeys()
                .forEach(s -> valueOperations.set(s, s));
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
