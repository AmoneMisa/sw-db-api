package com.example.demo.controller;

import com.example.demo.component.DataLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/load")
public class LoadController {
    private final DataLoader dataLoader;

    @GetMapping
    public void load() {
        dataLoader.load();
    }
}
