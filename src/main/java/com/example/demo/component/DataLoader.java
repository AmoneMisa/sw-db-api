package com.example.demo.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader {
    private final EffectLoader effectLoader;
    private final FusionsLoader fusionsLoader;
    private final HomunculusSkillLoader homunculusSkillLoader;
    private final LeaderSkillLoader leaderSkillLoader;
    private final MonsterLoader monsterLoader;
    private final SkillLoader skillLoader;

    public void load() {
        fusionsLoader.preload();
        monsterLoader.preload();
        homunculusSkillLoader.preload();
        skillLoader.preload();
        leaderSkillLoader.preload();
        effectLoader.preload();

        effectLoader.loadAll();
        leaderSkillLoader.loadAll();
        skillLoader.loadAll();
        homunculusSkillLoader.loadAll();
        monsterLoader.loadAll();
        fusionsLoader.loadAll();

        log.info("Loaded all");
    }
}
