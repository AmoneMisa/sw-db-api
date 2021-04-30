package com.example.demo.component.mapper;

import com.example.demo.dto.MonsterDto;
import com.example.demo.model.HomunculusSkill;
import com.example.demo.model.LeaderSkill;
import com.example.demo.model.Monster;
import com.example.demo.model.Skill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MonsterMapper {
    MonsterDto toDto(Monster monster);

    default Integer skill(Skill skill) {
        return skill.getId();
    }

    default Integer homunculusSkill(HomunculusSkill homunculusSkill) {
        return homunculusSkill.getId();
    }

    default Integer leaderSkill(LeaderSkill leaderSkill) {
        if (leaderSkill == null) {
            return null;
        }
        return leaderSkill.getId();
    }
}
