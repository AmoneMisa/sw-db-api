package com.example.demo.controller;

import com.example.demo.component.SkillLoader;
import com.example.demo.component.mapper.SkillMapper;
import com.example.demo.dto.SkillDto;
import com.example.demo.repository.SkillRepository;
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
@RequestMapping("/skills")
public class SkillController {
    private final SkillRepository skillRepository;
    private final SkillLoader skillLoader;
    private final SkillMapper skillMapper;

    @GetMapping
    public Page<SkillDto> getAll(Pageable pageable) {
        return skillRepository.findAll(pageable)
                .map(skillMapper::toDto);
    }

    @GetMapping("/{id}")
    public SkillDto getOne(@PathVariable int id) {
        return skillRepository.findById(id)
                .map(skillMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill with this id not found"));
    }

    @GetMapping("/load")
    public String load() {
        skillLoader.loadAll();
        return "loaded";
    }
}
