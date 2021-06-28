package com.MIW.Cohort5.Clups.repository;

import com.MIW.Cohort5.Clups.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserCode(Integer userCode);

    @Query("SELECT userCode FROM User WHERE fullName LIKE :request%")
    List<Integer> findUserByPartialString(@Param("request") String request);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);
}
