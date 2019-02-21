package com.example.tacocloud.repositories;

import com.example.tacocloud.models.Ingredient;

public interface IIngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findById(String id);

    Ingredient save(Ingredient ingredient);
}
