package com.example.demo.controller;

import com.example.demo.component.LeaderSkillLoader;
import com.example.demo.util.SpecificationUtil;
import com.example.demo.component.mapper.LeaderSkillMapper;
import com.example.demo.dto.LeaderSkillDto;
import com.example.demo.repository.LeaderSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leader-skill")
public class LeaderSkillController {
    private final LeaderSkillRepository leaderSkillRepository;
    private final LeaderSkillLoader leaderSkillLoader;
    private final LeaderSkillMapper leaderSkillMapper;

    @GetMapping
    public Page<LeaderSkillDto> getAll(Pageable pageable) {
        return leaderSkillRepository.findAll(pageable)
                .map(leaderSkillMapper::toDto);
    }

    @GetMapping("/{id}")
    public LeaderSkillDto getOne(@PathVariable int id) {
        return leaderSkillRepository.findById(id)
                .map(leaderSkillMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leader skill with this id not found"));
    }

    @GetMapping("/load")
    public String load() {
        leaderSkillLoader.loadAll();
        return "loaded";
    }
}
