package com.arsen.silchenko.testexerciseforpumb.specificationprovider.animal;

import com.arsen.silchenko.testexerciseforpumb.model.Animal;
import com.arsen.silchenko.testexerciseforpumb.specificationprovider.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class TypeSpecificationProvider implements SpecificationProvider<Animal> {
    private static final String TYPE = "type";

    @Override
    public String getKey() {
        return TYPE;
    }

    public Specification<Animal> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root
                .get(TYPE).in(Arrays.stream(params).toArray());
    }
}
