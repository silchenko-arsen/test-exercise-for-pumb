package com.arsen.silchenko.testexerciseforpumb.specificationprovider.animal;

import com.arsen.silchenko.testexerciseforpumb.model.Animal;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.arsen.silchenko.testexerciseforpumb.specificationprovider.SpecificationProvider;
import java.util.Arrays;

@Component
public class CategorySpecificationProvider implements SpecificationProvider<Animal> {
    private static final String CATEGORY = "category";

    @Override
    public String getKey() {
        return CATEGORY;
    }

    public Specification<Animal> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root
                .get(CATEGORY).in(Arrays.stream(params).toArray());
    }
}
