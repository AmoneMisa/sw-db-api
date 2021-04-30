package com.example.demo.component.mapper;

import com.example.demo.dto.FusionDto;
import com.example.demo.dto.LeaderSkillDto;
import com.example.demo.model.Fusion;
import com.example.demo.model.LeaderSkill;
import com.example.demo.model.Monster;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeaderSkillMapper {
   LeaderSkillDto toDto(LeaderSkill leaderSkill);
}
