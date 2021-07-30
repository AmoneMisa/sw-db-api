package com.example.demo.controller;

import com.example.demo.component.DataLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/load")
public class LoadController {
    @Value( "${sw-db.load-password}" )
    private final String loadPassword;
    private final DataLoader dataLoader;

    @GetMapping
    public void load(@RequestParam String password) {
        if (!password.equals(loadPassword)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        dataLoader.load();
    }
}
