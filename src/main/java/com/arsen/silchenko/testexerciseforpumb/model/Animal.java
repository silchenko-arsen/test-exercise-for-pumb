package com.silchenko.arsen.testpumbproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String gender;

    @Positive
    private Integer weight;

    @Positive
    private Integer cost;

    @Positive
    @Enumerated(EnumType.ORDINAL)
    private Category category;

    public enum Category {
        FIRST, SECOND, THIRD, FOURTH
    }
}

