package com.example.tacocloud.repositories;

import com.example.tacocloud.models.User;
import org.springframework.data.repository.CrudRepository;

public interface JpaUserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
