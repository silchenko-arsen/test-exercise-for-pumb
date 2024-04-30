package com.arsen.silchenko.testexerciseforpumb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "animals")
@Getter
@Setter
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Column(nullable = false)
    private Integer weight;
    @Column(nullable = false)
    private Integer cost;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    public Animal(String name, Type type, Sex sex, Integer weight, Integer cost) {
        this.name = name;
        this.type = type;
        this.sex = sex;
        this.weight = weight;
        this.cost = cost;
    }

    public Animal setCategory() {
        if (cost >= 0 && cost <= 20) {
            category = Animal.Category.FIRST;
        } else if (cost <= 40) {
            category = Animal.Category.SECOND;
        } else if (cost <= 60) {
            category = Animal.Category.THIRD;
        } else {
            category = Animal.Category.FOURTH;
        }
        return this;
    }

    public enum Type {
        CAT, DOG
    }

    public enum Sex {
        MALE, FEMALE
    }

    public enum Category {
        FIRST, SECOND, THIRD, FOURTH
    }
}

