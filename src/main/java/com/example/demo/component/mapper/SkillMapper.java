package com.example.demo.component.mapper;

import com.example.demo.dto.SkillDto;
import com.example.demo.model.Monster;
import com.example.demo.model.Skill;
import com.example.demo.model.SkillEffect;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = SkillEffectMapper.class)
public interface SkillMapper {
    SkillDto toDto(Skill skill);

    default Integer monster(Monster monster) {
        return monster.getId();
    }
}
