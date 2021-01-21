package com.example.urlkeygenerator.service;

import com.example.urlkeygenerator.generator.KeyGenerator;
import org.springframework.stereotype.Service;

@Service
public class GenerationService {

    private final KeyGenerator keyGenerator;

    public GenerationService(final KeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }


}
