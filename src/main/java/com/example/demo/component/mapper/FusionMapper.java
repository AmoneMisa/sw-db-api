package com.example.demo.component.mapper;

import com.example.demo.dto.FusionDto;
import com.example.demo.model.Fusion;
import com.example.demo.model.Monster;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FusionMapper {
    FusionDto toDto(Fusion fusion);

    default Integer monster(Monster monster) {
        return monster.getId();
    }
}
