package com.MIW.Cohort5.Clups.repository;

import com.MIW.Cohort5.Clups.model.ClupsUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClupsUserRepository extends JpaRepository<ClupsUser, Integer> {

    Optional<ClupsUser> findByUsername(String username);

}
