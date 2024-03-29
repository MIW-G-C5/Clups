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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private static final String STANDARD_ROLE = "CUSTOMER";

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
    public void newUser(UserDto userDto) {

        userDto.setUserCode(makeUserCode(userDto));
        userDto.setUsername(makeUserName(userDto));
        userDto.setPassword(makePassword(userDto));
        userDto.setUserRole(makeRole(userDto));
        userDto.setPrepaidBalance(makePrepaidBalance(userDto));

        saveUser(userDto);
    }

    @Override
    public void editUser(UserDto userDto) {

        userDto.setUserCode(makeUserCode(userDto));
        userDto.setUsername(makeUserName(userDto));
        editRole(userDto);

        saveUser(userDto);
    }

    @Override
    public void saveUser(UserDto userDto) {

        Role role = roleService.findModelByName(userDto.getUserRole());

        User newUser = dtoConverter.toModel(role, userDto);
        User oldUser = userRepository.findUserByUserCode(userDto.getUserCode());

        if (oldUser != null) {
            newUser.setUserId(oldUser.getUserId());
        }

        addUser(newUser);
    }

    private UserDto editRole(UserDto userDto) {

        if (userDto.getUserRole() == null || loggedInUser(userDto)) {
            userDto.setUserRole(userRepository.findUserByUserCode(userDto.getUserCode()).getRole().getRoleName());
        }

        return userDto;
    }

    private String makeRole(UserDto userDto) {
        String userRole;

        if (userDto.getUserRole() == null) {
            userRole = STANDARD_ROLE;
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

        int userCode;

        if (userDto.getUserCode() <= 0) {
            userCode = getHighestUserCode() + 1;
        } else {
            userCode = userDto.getUserCode();
        }

        return userCode;
    }

    @Override
    public void addUser(User user) {
        if (user.getPassword() != null) {
            user.setPassword(encodePassword(user.getPassword()));
        } else {
            user.setPassword(userRepository.findUserByUserCode(user.getUserCode()).getPassword());
        }

        // ensure users created in the seeder get a unique usercode
        if (user.getUserCode() <= 0) {
            user.setUserCode(getHighestUserCode() + 1);
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

    @Override
    public UserDto findDtoByUserCode(Integer userCode){
        User user = userRepository.findUserByUserCode(userCode);
        return dtoConverter.toDto(user);
    }

    @Override
    public User findModelByUserCode(Integer userCode){
        return userRepository.findUserByUserCode(userCode);
    }

    @Override
    public User deleteUser(UserDto userDto){

        if (loggedInUser(userDto)) {
            throw new IllegalArgumentException("You are not allowed to remove your own account");
        } else {
            User model = findModelByUserCode(userDto.getUserCode());
            userRepository.delete(model);
            return model;
        }
    }

    @Override
    public void addCredit(Integer userCode, BigDecimal amount) {
        User user = userRepository.findUserByUserCode(userCode);

        if (user.getPrepaidBalance() == null) {
            user.setPrepaidBalance(amount);
        } else {
            user.setPrepaidBalance(user.getPrepaidBalance().add(amount));
        }

        userRepository.save(user);
    }

    @Override
    public List<Integer> getUserByPartialString(String request) {
        return userRepository.findUserByPartialString(request);
    }

    @Override
    public void payWithCredit(Integer userCode, BigDecimal orderTotal) {
        User user = userRepository.findUserByUserCode(userCode);

        if (isBalanceSufficient(userCode, orderTotal)) {
            user.setPrepaidBalance(user.getPrepaidBalance().subtract(orderTotal));
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("The balance is not sufficient for this order");
        }
    }

    // Last equals("BARMANAGER") in return statement is purely for the demo.
    // Should be deleted afterwards and fixed in another way.
    @Override
    public boolean loggedInUser(UserDto userDto) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

        if (userDto != null) {
            return loggedInUser.getName().equals(userDto.getUsername()) && userDto.getUserRole().equals("BARMANAGER");
        } else {
            return false;
        }
    }

    @Override
    public boolean isBalanceSufficient(Integer userCode, BigDecimal orderTotal) {
        User user = userRepository.findUserByUserCode(userCode);

        if (user.getPrepaidBalance().compareTo(orderTotal) >= 0) {
            return true;
        } else {
            return false;
        }
    }
}

