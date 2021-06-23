package com.MIW.Cohort5.Clups.services.dtoConverters;

import com.MIW.Cohort5.Clups.dtos.UserDto;
import com.MIW.Cohort5.Clups.model.Role;
import com.MIW.Cohort5.Clups.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * This class converts users to a DTO to filter out any unnecessary data.
 */

public class UserDtoConverter {

    public List<UserDto> toUserDtos(List<User> models) {
        List<UserDto> result = new ArrayList<>();

        for (User model : models) {
            UserDto dto = toDto(model);
            result.add(dto);
        }

        return result;
    }

    public UserDto toDto(User model) {
        UserDto result = new UserDto();
        result.setUsername(model.getUsername());
        result.setPassword(model.getPassword());
        result.setUserCode(model.getUserCode());
        result.setFullName(model.getFullName());
        result.setPrepaidBalance(model.getPrepaidBalance());
        result.setUserRole(model.getRole().getRoleName());

        return result;
    }

    public User toModel(Role role, UserDto dto) {
        User model = new User();

        model.setUsername(dto.getUsername());
        model.setPassword(dto.getPassword());
        model.setUserCode(dto.getUserCode());
        model.setFullName(dto.getFullName());
        model.setPrepaidBalance(dto.getPrepaidBalance());
        model.setRole(role);

        return model;
    }

}


