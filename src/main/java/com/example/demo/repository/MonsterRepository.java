package com.example.demo.repository;

import com.example.demo.model.Monster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MonsterRepository extends JpaRepository<Monster, Integer>,
        JpaSpecificationExecutor<Monster> {
    @Query("SELECT m FROM Monster m WHERE m.element = :element" +
            " AND m.awakenLevel = :awakenLevel" +
            " AND m.baseStars = :baseStars" +
            " AND m.source = true" +
            " ORDER BY function('RAND')")
    List<Monster> findRandomMonsters(
            @Param("element") String element,
            @Param("awakenLevel") Integer awakenLevel,
            @Param("baseStars") Integer baseStars,
            Pageable pageable
    );
}
