package com.example.tacocloud.repositories;

import com.example.tacocloud.models.Taco;
import org.springframework.data.repository.CrudRepository;

public interface JpaTacoRepository extends CrudRepository<Taco, Long> {
}
