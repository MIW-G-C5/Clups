package com.MIW.Cohort5.Clups.repository;

import com.MIW.Cohort5.Clups.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByRoleName(String name);

    Role findRoleById(Integer id);

}
