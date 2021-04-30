package com.example.demo.component.mapper;

import com.example.demo.dto.HomunculusSkillDto;
import com.example.demo.dto.SkillDto;
import com.example.demo.model.HomunculusSkill;
import com.example.demo.model.Monster;
import com.example.demo.model.Skill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HomunculusSkillMapper {
    HomunculusSkillDto toDto(HomunculusSkill homunculusSkill);

    default Integer monster(Monster monster) {
        return monster.getId();
    }
    default Integer skill(Skill skill) {
        return skill.getId();
    };
}
