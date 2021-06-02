package com.MIW.Cohort5.Clups.services.dtoConverters;

import com.MIW.Cohort5.Clups.dtos.UserDto;
import com.MIW.Cohort5.Clups.model.ClupsUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * This class converts users to a DTO to filter out any unnecessary data.
 */

public class UserDtoConverter {

    public List<UserDto> toUserDtos(List<ClupsUser> models) {
        List<UserDto> result = new ArrayList<>();

        for (ClupsUser model : models) {
            UserDto dto = toDto(model);
            result.add(dto);
        }

        return result;
    }

    public UserDto toDto(ClupsUser model) {
        UserDto result = new UserDto();
        result.setUsername(model.getUsername());

        return result;
    }

}


