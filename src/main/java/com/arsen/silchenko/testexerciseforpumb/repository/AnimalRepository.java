package com.silchenko.arsen.testpumbproject.repository;

import com.silchenko.arsen.testpumbproject.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
