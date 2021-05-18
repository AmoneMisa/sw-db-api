package com.example.demo.component.mapper;

import com.example.demo.dto.MonsterDto;
import com.example.demo.dto.MonsterItemDto;
import com.example.demo.model.Monster;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SkillMapper.class, LeaderSkillMapper.class, HomunculusSkillMapper.class})
public interface MonsterMapper {
    MonsterItemDto toItemDto(Monster monster);

    MonsterDto toDto(Monster monster);
}
