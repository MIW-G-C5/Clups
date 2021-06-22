package com.MIW.Cohort5.Clups.repository;

import com.MIW.Cohort5.Clups.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

}
