package com.example.demo.component.mapper;

import com.example.demo.dto.EffectDto;
import com.example.demo.model.Effect;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EffectMapper {
    EffectDto toDto(Effect effect);
}
