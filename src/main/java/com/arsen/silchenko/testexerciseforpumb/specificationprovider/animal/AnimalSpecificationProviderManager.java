package com.arsen.silchenko.testexerciseforpumb.specificationprovider.animal;

import com.arsen.silchenko.testexerciseforpumb.model.Animal;

import com.arsen.silchenko.testexerciseforpumb.specificationprovider.SpecificationProvider;
import com.arsen.silchenko.testexerciseforpumb.specificationprovider.SpecificationProviderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AnimalSpecificationProviderManager implements SpecificationProviderManager<Animal> {
    private final List<SpecificationProvider<Animal>> animalSpecificationProviders;

    @Autowired
    public AnimalSpecificationProviderManager(List<SpecificationProvider<Animal>> animalSpecificationProviders) {
        this.animalSpecificationProviders = animalSpecificationProviders;
    }

    @Override
    public SpecificationProvider<Animal> getSpecificationProvider(String key) {
        return animalSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Can't find correct specification provider for key " + key));
    }
}
