package com.example.urlkeygenerator.api;

import com.example.urlkeygenerator.model.Segment;
import com.example.urlkeygenerator.service.GenerationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;


@RestController
public class GenerationController {

    private final GenerationService generationService;

    public GenerationController(GenerationService generationService) {
        this.generationService = generationService;
    }


    @PostMapping("/generate")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void generate(){
        generationService.checkIsFree();
        generationService.generate();
    }

}
