package com.example.demo.service;

import com.example.demo.model.Monster;
import com.example.demo.model.SummonScroll;
import com.example.demo.repository.MonsterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MonsterService {
    private final MonsterRepository monsterRepository;

    public Optional<Monster> getRandomMonster(SummonScroll summonScroll) {

        Random random = new Random();
        int elementIndex = random.nextInt(summonScroll.getElements().size());
        String element = summonScroll.getElements().get(elementIndex);
        double randForStars = random.nextDouble();
        int baseStars;
        int awakenLevel;

        if (randForStars < summonScroll.getChance5Stars()) {
            baseStars = 5;
            awakenLevel = 0;
        } else if (randForStars < summonScroll.getChance4Stars() + summonScroll.getChance5Stars()) {
            baseStars = 4;
            double randForAwakening = random.nextDouble();

            if (randForAwakening < summonScroll.getChanceAwaken4Stars()) {
                awakenLevel = 1;
                baseStars = 5;
            } else {
                awakenLevel = 0;
            }
        } else {
            baseStars = 3;
            double randForAwakening = random.nextDouble();

            if (randForAwakening < summonScroll.getChanceAwaken3Stars()) {
                baseStars = 4;
                awakenLevel = 1;
            } else {
                awakenLevel = 0;
            }
        }

        return monsterRepository.findRandomMonsters(element, awakenLevel, baseStars, PageRequest.of(0, 1))
                .stream()
                .findFirst();
    }
}