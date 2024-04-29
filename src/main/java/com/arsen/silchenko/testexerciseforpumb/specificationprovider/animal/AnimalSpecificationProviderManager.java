package com.arsen.silchenko.testexerciseforpumb.specificationprovider.animal;

import com.arsen.silchenko.testexerciseforpumb.model.Animal;

import com.arsen.silchenko.testexerciseforpumb.specificationprovider.SpecificationProvider;
import com.arsen.silchenko.testexerciseforpumb.specificationprovider.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Animal> {
    private final List<SpecificationProvider<Animal>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<Animal> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Can't find correct specification provider for key " + key));
    }
}
