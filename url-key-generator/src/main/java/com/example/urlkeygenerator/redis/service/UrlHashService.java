package com.example.urlkeygenerator.redis.service;

import org.springframework.stereotype.Service;

@Service
public class UrlHashService {


    private final String URLHASH_CACHE = "EMPLOYEE";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, Employee> hashOperations;

}
