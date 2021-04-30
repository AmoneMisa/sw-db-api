package com.example.demo.controller;

import com.example.demo.component.HomunculusSkillLoader;
import com.example.demo.component.mapper.HomunculusSkillMapper;
import com.example.demo.dto.HomunculusSkillDto;
import com.example.demo.repository.HomunculusSkillRepository;
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
@RequestMapping("/homunculus-skill")
public class HomunculusSkillController {
    private final HomunculusSkillRepository homunculusSkillRepository;
    private final HomunculusSkillLoader homunculusSkillLoader;
    private final HomunculusSkillMapper homunculusSkillMapper;

    @GetMapping
    public Page<HomunculusSkillDto> getAll(Pageable pageable) {
        return homunculusSkillRepository.findAll(pageable)
                .map(homunculusSkillMapper::toDto);
    }

    @GetMapping("/{id}")
    public HomunculusSkillDto getOne(@PathVariable int id) {
        return homunculusSkillRepository.findById(id)
                .map(homunculusSkillMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Homunculus skill with this id not found"));
    }

    @GetMapping("/load")
    public String load() {
        homunculusSkillLoader.loadAll();
        return "loaded";
    }
}
