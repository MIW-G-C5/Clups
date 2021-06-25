package com.MIW.Cohort5.Clups;

import com.MIW.Cohort5.Clups.dtos.UserDto;
import com.MIW.Cohort5.Clups.model.Role;
import com.MIW.Cohort5.Clups.model.User;
import com.MIW.Cohort5.Clups.services.dtoConverters.UserDtoConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * unit test for the User/UserDto/DtoConverter
 */
public class UserTests {

    @Test
    void testModelToDtoConversion(){
        UserDtoConverter dtoConverter = new UserDtoConverter();

        User testUser = new User(
                "test",
                "pw",
                "fullname",
                new Role("Tester"),
                BigDecimal.valueOf(3));

        UserDto testUserDto = dtoConverter.toDto(testUser);

        Assertions.assertAll(
                () -> Assertions.assertEquals(testUser.getUsername(), testUserDto.getUsername()),
                () -> Assertions.assertEquals(testUser.getPassword(), testUserDto.getPassword()),
                () -> Assertions.assertEquals(testUser.getFullName(), testUserDto.getFullName()),
                () -> Assertions.assertEquals(testUser.getRole().getRoleName(), testUserDto.getUserRole()),
                () -> Assertions.assertEquals(testUser.getPrepaidBalance(), testUserDto.getPrepaidBalance())
                );
    }

    @Test
    void testDtoToModel() {
        UserDtoConverter dtoConverter = new UserDtoConverter();

        Role testRole = new Role("Tester");
        UserDto testUserDto = new UserDto(
                "test",
                "pw",
                "fullname",
                BigDecimal.valueOf(3),
                testRole.getRoleName());

        User testUser = dtoConverter.toModel(testRole, testUserDto);

        Assertions.assertAll(
                () -> Assertions.assertEquals(testUserDto.getUsername(), testUser.getUsername()),
                () -> Assertions.assertEquals(testUserDto.getPassword(), testUser.getPassword()),
                () -> Assertions.assertEquals(testUserDto.getFullName(), testUser.getFullName()),
                () -> Assertions.assertEquals(testUserDto.getPrepaidBalance(), testUser.getPrepaidBalance()),
                () -> Assertions.assertEquals(testUserDto.getUserRole(), testUser.getRole().getRoleName())
                );
    }

    @Test
    void testAddBalance() {
        User testUser1 = new User();
        User testUser2 = new User();

        testUser2.setPrepaidBalance(BigDecimal.TEN);

        testUser1.addToBalance(10);
        testUser2.addToBalance(10);

        Assertions.assertEquals(BigDecimal.valueOf(10), testUser1.getPrepaidBalance());
        Assertions.assertEquals(BigDecimal.valueOf(20), testUser2.getPrepaidBalance());
    }
}
