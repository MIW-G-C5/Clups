package com.MIW.Cohort5.Clups.services.implementations;

import com.MIW.Cohort5.Clups.dtos.UserDto;
import com.MIW.Cohort5.Clups.model.MyUserDetails;
import com.MIW.Cohort5.Clups.model.Role;
import com.MIW.Cohort5.Clups.model.User;
import com.MIW.Cohort5.Clups.repository.UserRepository;
import com.MIW.Cohort5.Clups.services.RoleService;
import com.MIW.Cohort5.Clups.services.UserService;
import com.MIW.Cohort5.Clups.services.dtoConverters.UserDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * This service makes a connection with the user repository.
 */

@Service
public class UserDetailsServiceImpl implements UserService {

    // these constants are used when making a new user and these attributes are not given.
    private static final BigDecimal INITIAL_PREPAID_BALANCE = BigDecimal.ZERO;
    private static final String INITIAL_PASSWORD_ROOT = "pw";
    private static final String INITIAL_USERNAME_ROOT = "user";

    private UserDtoConverter dtoConverter = new UserDtoConverter();

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + "was not found");
        }

        return new MyUserDetails(user);
    }

    @Override
    public List<UserDto> getAll() {

        List<User> models = userRepository.findAll();
        return dtoConverter.toUserDtos(models);
    }

    @Override
    public void saveUser(UserDto userDto) {

        userDto.setUserCode(makeUserCode(userDto));
        userDto.setUsername(makeUserName(userDto));
        userDto.setPassword(makePassword(userDto));
        userDto.setUserRole(makeRole(userDto));
        userDto.setPrepaidBalance(makePrepaidBalance(userDto));

        Role role = roleService.findModelByName(userDto.getUserRole());
        User newUser = dtoConverter.toModel(role, userDto);

        addUser(newUser);
    }

    private String makeRole(UserDto userDto) {
        String userRole;

        if (userDto.getUserRole() == null) {
            userRole = "CUSTOMER";
        } else {
            userRole = userDto.getUserRole();
        }

        return userRole;
    }

    private BigDecimal makePrepaidBalance(UserDto userDto) {
        BigDecimal prepaidBalance;

        if (userDto.getPrepaidBalance() == null) {
            prepaidBalance = INITIAL_PREPAID_BALANCE;
        } else {
            prepaidBalance = userDto.getPrepaidBalance();
        }

        return prepaidBalance;
    }

    private String makePassword(UserDto userDto) {
        String password;

        if (userDto.getPassword() == null || userDto.getPassword() == "") {
            password = INITIAL_PASSWORD_ROOT + userDto.getUserCode();
        } else {
            password = userDto.getPassword();
        }

        return password;
    }

    private String makeUserName(UserDto userDto) {
        String userName;

        if (userDto.getUsername() == null || userDto.getUsername() == "") {
            userName = INITIAL_USERNAME_ROOT + userDto.getUserCode();
        } else {
            userName = userDto.getUsername();
        }

        return userName;
    }

    private Integer makeUserCode(UserDto userDto) {
        int userCode = 0;

        if (userDto.getUserCode() <= 0) {
            userCode = getHighestUserCode() + 1;
        }

        return userCode;
    }

    @Override
    public void addUser(User user) {
        if (user.getPassword() != null) {
            user.setPassword(encodePassword(user.getPassword()));
        }

        userRepository.save(user);
    }

    private String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encodedPassword = encoder.encode(password);

        return encodedPassword;
    }

    @Override
    public Integer getHighestUserCode() {
        Integer userCode = 0;

        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            if (user.getUserCode() > userCode) {
                userCode = user.getUserCode();
            }
        }

        return userCode;
    }

}

