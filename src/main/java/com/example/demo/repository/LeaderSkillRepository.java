package com.example.demo.repository;

import com.example.demo.model.LeaderSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LeaderSkillRepository extends JpaRepository<LeaderSkill, Integer>,
        JpaSpecificationExecutor<LeaderSkill> {}
