package com.MIW.Cohort5.Clups.services;

import com.MIW.Cohort5.Clups.dtos.UserDto;
import com.MIW.Cohort5.Clups.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.math.BigDecimal;
import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserDto> getAll();

    void newUser(UserDto userDto);

    void addUser(User user);

    void editUser(UserDto userDto);

    void saveUser(UserDto userDto);

    Integer getHighestUserCode();

    UserDto findDtoByUserCode(Integer userCode);

    User deleteUser(UserDto userDto);

    User findModelByUserCode (Integer userCode);

    void addCredit(Integer userCode, BigDecimal amount);

    List<Integer> getUserByPartialString(String request);

    void payWithCredit(Integer userCode, BigDecimal orderTotal);

    boolean loggedInUser(UserDto userDto);

    boolean isBalanceSufficient(Integer userCode, BigDecimal orderTotal);

}
