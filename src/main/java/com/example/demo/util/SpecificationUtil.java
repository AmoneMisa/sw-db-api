package com.example.demo.util;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationUtil {
    public static <T> Specification<T> equals(String fieldName, String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get(fieldName)), value.toLowerCase());
    }

    public static <T> Specification<T> equals(String fieldName, Integer value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(fieldName).as(Integer.class), value);
    }
    public static <T> Specification<T> equals(String fieldName, Boolean value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(fieldName).as(Boolean.class), value);
    }

    public static <T> Specification<T> lessThanOrEqualTo(String fieldName, Integer value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(fieldName).as(Integer.class), value);
    }

    public static <T> Specification<T> greaterThanOrEqualTo(String fieldName, Integer value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName).as(Integer.class), value);
    }
}
