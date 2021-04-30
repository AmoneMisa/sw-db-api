package com.example.demo.controller;

import com.example.demo.component.FusionsLoader;
import com.example.demo.component.mapper.FusionMapper;
import com.example.demo.dto.FusionDto;
import com.example.demo.repository.FusionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fusions")
public class FusionsController {
    private final FusionsRepository fusionsRepository;
    private final FusionsLoader fusionsLoader;
    private final FusionMapper fusionMapper;

    @GetMapping
    public Page<FusionDto> getAll(Pageable pageable) {
        return fusionsRepository.findAll(pageable)
                .map(fusionMapper::toDto);
    }

    @GetMapping("/{id}")
    public FusionDto getOne(@PathVariable int id) {
        return fusionsRepository.findById(id)
                .map(fusionMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fusion with this id not found"));
    }

    @GetMapping("/load")
    public String load() {
        fusionsLoader.loadAll();
        return "loaded";
    }
}
