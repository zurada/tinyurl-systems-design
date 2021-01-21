package com.example.urlkeygenerator.service;

import com.example.urlkeygenerator.exception.CustomAsyncExceptionHandler;
import com.example.urlkeygenerator.exception.GenerationInProgressException;
import com.example.urlkeygenerator.generator.KeyGenerator;
import com.example.urlkeygenerator.model.GenerationState;
import com.example.urlkeygenerator.model.Segment;
import com.example.urlkeygenerator.model.SegmentKeys;
import com.example.urlkeygenerator.redis.service.UrlHashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class GenerationService {

    private final Logger logger = LoggerFactory.getLogger(GenerationService.class);

    private final KeyGenerator keyGenerator;
    private final UrlHashService urlHashService;

    public GenerationService(final KeyGenerator keyGenerator, UrlHashService urlHashService) {
        this.keyGenerator = keyGenerator;
        this.urlHashService = urlHashService;
    }

    public void checkIsFree() {
        GenerationState state = urlHashService.getState();
        if (state == GenerationState.BUSY) {
            throw new GenerationInProgressException();
        }
    }

    @Async("asyncExecutor")
    public void generate() {
        urlHashService.setState(GenerationState.BUSY);
        logger.info("Generating new keys");
        Segment lastSegment = urlHashService.getLastSegment();
        SegmentKeys generatedKeys = keyGenerator.generate(lastSegment);
        urlHashService.save(generatedKeys);
        urlHashService.setState(GenerationState.FREE);
        logger.info("Generating new keys finished");
    }

}
