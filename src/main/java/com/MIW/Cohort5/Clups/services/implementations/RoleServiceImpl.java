package com.MIW.Cohort5.Clups.services.implementations;

import com.MIW.Cohort5.Clups.model.Role;
import com.MIW.Cohort5.Clups.repository.RoleRepository;
import com.MIW.Cohort5.Clups.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 */

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findModelByName(String roleName) {

        if(roleRepository.findRoleByRoleName(roleName) != null) {
            return roleRepository.findRoleByRoleName(roleName);
        } else {
            return addNewRole(roleName);
        }
    }

    @Override
    public Role addNewRole(String roleName) {

        roleRepository.save(new Role(roleName));
        return roleRepository.findRoleByRoleName(roleName);
    }
}
