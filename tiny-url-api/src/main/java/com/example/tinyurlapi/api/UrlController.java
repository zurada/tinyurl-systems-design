package com.example.tinyurlapi.api;

import com.example.tinyurlapi.model.UrlEntry;
import com.example.tinyurlapi.model.UrlEntryRequest;
import com.example.tinyurlapi.service.UrlToKeyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UrlController {

    private final UrlToKeyService urlToKeyService;
    private final String mainPageUrl;

    public UrlController(UrlToKeyService urlToKeyService, @Value("${main-page.url}") String mainPageUrl) {
        this.urlToKeyService = urlToKeyService;
        this.mainPageUrl = mainPageUrl;
    }

    @CrossOrigin(originPatterns = "*")
    @RequestMapping(value = "/redirect/{key}", method = RequestMethod.GET)
    public void redirect(HttpServletResponse httpServletResponse, @PathVariable String key) {
        httpServletResponse.setHeader("Location", urlToKeyService.getUrlByKey(key)
                .orElse(mainPageUrl));
        httpServletResponse.setStatus(302);
    }

    @CrossOrigin(originPatterns = "*")
    @PostMapping("/create")
    public UrlEntry createUrl(@RequestBody UrlEntryRequest urlEntry) {
        return urlToKeyService.createUrl(urlEntry);
    }

}
