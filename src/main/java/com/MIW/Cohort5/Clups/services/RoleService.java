package com.MIW.Cohort5.Clups.services;

import com.MIW.Cohort5.Clups.model.Role;

public interface RoleService {

    Role findModelByName(String roleName);

    Role addNewRole(String rolename);


}
