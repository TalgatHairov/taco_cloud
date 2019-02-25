package com.example.tacocloud.repositories;

import com.example.tacocloud.models.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
