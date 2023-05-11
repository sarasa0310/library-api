package com.codestates.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNameAndPhone(String name, String phone);

    Optional<User> findByNameAndPhone(String name, String phone);

}
