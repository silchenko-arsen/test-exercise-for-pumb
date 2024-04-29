package com.arsen.silchenko.testexerciseforpumb.specificationprovider.animal;

import com.example.model.Book;
import com.example.specificationprovider.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    private static final String AUTHOR = "author";

    @Override
    public String getKey() {
        return AUTHOR;
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root
                .get(AUTHOR).in(Arrays.stream(params).toArray());
    }
}
