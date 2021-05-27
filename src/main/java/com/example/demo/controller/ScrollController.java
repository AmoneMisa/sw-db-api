package com.example.demo.controller;

import com.example.demo.component.mapper.MonsterMapper;
import com.example.demo.dto.MonsterItemDto;
import com.example.demo.model.SummonScroll;
import com.example.demo.service.MonsterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scroll")
public class ScrollController {
    private final MonsterService monsterService;
    private final MonsterMapper monsterMapper;

    @GetMapping
    public List<MonsterItemDto> getOne(@RequestParam SummonScroll.Type type, @RequestParam Integer summonsCount) {
        List<MonsterItemDto> monsters = new ArrayList<>();

        summonsCount = Math.max(1, Math.min(100, summonsCount));

        for (int i = 0; i < summonsCount; i++) {
            MonsterItemDto monster = monsterService.getRandomMonster(type.getScroll())
                    .map(monsterMapper::toItemDto)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Monster with this id not found"));

            monsters.add(monster);
        }

        return monsters;
    }
}
