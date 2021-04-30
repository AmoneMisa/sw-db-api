package com.example.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class MonstersImageLoader {
    public void downloadImage(String filename) {
        Path path = Paths.get("monsters", filename);
        if (Files.exists(path)) {
            log.info("File exists: {}", filename);
            return;
        }
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> response;
        String url = "https://swarfarm.com/static/herders/images/monsters/" + filename;
        log.info(url);

        try {
            response = restTemplate.getForEntity(url, byte[].class);

        } catch (HttpClientErrorException e) {
            log.error("HttpClientError: {}", e.getMessage());
            return;
        }
        if (response.getStatusCode() != HttpStatus.OK) {
            log.error("Response status: {}", response.getStatusCode());
            return;
        }

        try {
            Files.write(path, response.getBody());
        } catch (IOException e) {
            log.error("", e);
        }
    }
}
