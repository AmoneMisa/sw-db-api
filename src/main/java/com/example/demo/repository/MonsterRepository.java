package com.example.demo.repository;

import com.example.demo.model.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MonsterRepository extends JpaRepository<Monster, Integer>,
        JpaSpecificationExecutor<Monster> {
}
