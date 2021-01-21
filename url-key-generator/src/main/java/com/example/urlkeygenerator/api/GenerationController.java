package com.example.urlkeygenerator.api;

import com.example.urlkeygenerator.generator.KeyGenerator;
import com.example.urlkeygenerator.model.Segment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerationController {

    private final KeyGenerator keyGenerator;

    public GenerationController(KeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    @PostMapping("/generate")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void generate(){
        System.out.println(keyGenerator.generate(Segment.initialSegment()));
    }
}
