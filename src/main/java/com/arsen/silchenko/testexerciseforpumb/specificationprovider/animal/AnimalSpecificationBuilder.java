package com.arsen.silchenko.testexerciseforpumb.specificationprovider.animal;

import com.arsen.silchenko.testexerciseforpumb.dto.AnimalSearchParametersDto;
import com.arsen.silchenko.testexerciseforpumb.model.Animal;
import com.arsen.silchenko.testexerciseforpumb.specificationprovider.SpecificationBuilder;
import com.arsen.silchenko.testexerciseforpumb.specificationprovider.SpecificationProviderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AnimalSpecificationBuilder implements SpecificationBuilder<Animal> {
    private static final String TYPE = "type";
    private static final String SEX = "sex";
    private static final String CATEGORY = "category";
    private final SpecificationProviderManager<Animal> bookSpecificationProviderManager;

    @Autowired
    public AnimalSpecificationBuilder(SpecificationProviderManager<Animal> bookSpecificationProviderManager) {
        this.bookSpecificationProviderManager = bookSpecificationProviderManager;
    }

    @Override
    public Specification<Animal> build(AnimalSearchParametersDto searchParametersDto) {
        Specification<Animal> specification = Specification.where(null);
        if (searchParametersDto.types() != null
                && searchParametersDto.types().length > 0) {
            specification = specification
                    .and(bookSpecificationProviderManager.getSpecificationProvider(TYPE)
                    .getSpecification(searchParametersDto.types()));
        }
        if (searchParametersDto.sexes() != null
                && searchParametersDto.sexes().length > 0) {
            specification = specification
                    .and(bookSpecificationProviderManager.getSpecificationProvider(SEX)
                    .getSpecification(searchParametersDto.sexes()));
        }
        if (searchParametersDto.categories() != null
                && searchParametersDto.categories().length > 0) {
            specification = specification
                    .and(bookSpecificationProviderManager.getSpecificationProvider(CATEGORY)
                    .getSpecification(searchParametersDto.categories()));
        }
        return specification;
    }
}
