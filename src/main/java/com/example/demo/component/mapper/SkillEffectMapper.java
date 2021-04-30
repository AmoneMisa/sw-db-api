package com.example.demo.component.mapper;

import com.example.demo.dto.SkillEffectDto;
import com.example.demo.model.Effect;
import com.example.demo.model.SkillEffect;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillEffectMapper {
    SkillEffectDto toDto(SkillEffect skillEffect);

    default Integer effect(Effect effect) {
        return effect.getId();
    };
}
