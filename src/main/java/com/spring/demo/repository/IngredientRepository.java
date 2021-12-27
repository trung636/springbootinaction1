package com.spring.demo.repository;

import com.spring.demo.model.Ingredient;

public interface IngredientRepository {

	Iterable<Ingredient> findAll();

	Ingredient findOne(String id);

	Ingredient save(Ingredient ingredient);
}
