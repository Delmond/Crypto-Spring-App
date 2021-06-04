package com.innovic.crypto.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

import com.innovic.crypto.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
    boolean existsById(Long id);
    Optional<User> findOneByUsernameOrEmail(String username, String email);
}
