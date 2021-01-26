package com.example.tinyurlapi.service;

import com.example.tinyurlapi.model.RandomHash;
import com.example.tinyurlapi.model.UrlEntry;
import com.example.tinyurlapi.model.UrlEntryRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UrlToKeyService {

    private final String urlKeyGenerator;
    private final String urlKeyManager;
    private final Integer urlKeyGeneratorPort;
    private final Integer urlKeyManagerPort;
    private final RestTemplate restTemplate;
    private final Integer minAvailableKeys;

    public UrlToKeyService(@Value("${url-key-generator.host}") String urlKeyGenerator,
                           @Value("${url-key-manager.host}") String urlKeyManager,
                           @Value("${url-key-generator.port}") Integer urlKeyGeneratorPort,
                           @Value("${url-key-manager.port}") Integer urlKeyManagerPort, RestTemplate restTemplate,
                           @Value("${min.available.keys}") Integer minAvailableKeys) {
        this.urlKeyGenerator = urlKeyGenerator;
        this.urlKeyManager = urlKeyManager;
        this.urlKeyGeneratorPort = urlKeyGeneratorPort;
        this.urlKeyManagerPort = urlKeyManagerPort;
        this.restTemplate = restTemplate;
        this.minAvailableKeys = minAvailableKeys;
    }

    public Optional<String> getUrlByKey(String key) {
        ResponseEntity<UrlEntry> urlEntity = restTemplate.getForEntity("http://" + urlKeyManager + ":" + urlKeyManagerPort + "/get/" + key, UrlEntry.class);
        return Optional.ofNullable(urlEntity.getBody()).map(UrlEntry::getUrl);
    }

    public UrlEntry createUrl(UrlEntryRequest request) {
        Long availableKeys = restTemplate.getForObject("http://" + urlKeyManager + ":" + urlKeyManagerPort + "/countAvailableHashes", Long.class);
        if (availableKeys == null) {
            throw new IllegalArgumentException("Can't get information about availability");
        }
        if (availableKeys < minAvailableKeys) {
            restTemplate.postForLocation("http://" + urlKeyGenerator + ":" + urlKeyGeneratorPort + "/generate", null);
        }

        RandomHash randomHash = restTemplate.getForObject("http://" + urlKeyManager + ":" + urlKeyManagerPort + "/pollRandomHash", RandomHash.class);

        String key = randomHash.getKey();
        String url = request.getUrl();
        UrlEntry urlEntry = new UrlEntry(key, url);
        restTemplate.postForLocation("http://" + urlKeyManager + ":" + urlKeyManagerPort + "/create", urlEntry);

        return urlEntry;
    }

}
