package com.example.demo.controller;

import com.example.demo.component.EffectLoader;
import com.example.demo.component.mapper.EffectMapper;
import com.example.demo.dto.EffectDto;
import com.example.demo.repository.EffectRepository;
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
@RequestMapping("/effects")
public class EffectController {
    private final EffectRepository effectRepository;
    private final EffectLoader effectLoader;
    private final EffectMapper effectMapper;

    @GetMapping
    public Page<EffectDto> getAll(Pageable pageable) {
        return effectRepository.findAll(pageable)
                .map(effectMapper::toDto);
    }

    @GetMapping("/{id}")
    public EffectDto getOne(@PathVariable int id) {
        return effectRepository.findById(id)
                .map(effectMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Effect with this id not found"));
    }

    @GetMapping("/load")
    public String load() {
        effectLoader.loadAll();
        return "loaded";
    }
}
