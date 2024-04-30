package com.arsen.silchenko.testexerciseforpumb.specificationprovider;

public interface SpecificationProviderManager<T> {
    SpecificationProvider<T> getSpecificationProvider(String key);
}
