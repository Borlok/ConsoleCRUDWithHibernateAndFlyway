package com.borlok.repository;

import java.util.ArrayList;
import java.util.List;

public class CompositeRepository {
    private final List<GenericRepository<?,Integer>> repositories = new ArrayList<>();

    public CompositeRepository addRepository(GenericRepository<?,Integer> repository) {
        repositories.add(repository);
        return this;
    }

    public GenericRepository<? ,Integer> getRepository (GenericRepository<?,Integer> repository) {
        return repositories.stream()
                .filter(x -> x.toString().equals(repository.toString()))
                .findFirst()
                .orElse(null);
    }
}
