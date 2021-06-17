package com.MIW.Cohort5.Clups.services.implementations;

import com.MIW.Cohort5.Clups.dtos.UserDto;
import com.MIW.Cohort5.Clups.model.User;
import com.MIW.Cohort5.Clups.repository.UserRepository;
import com.MIW.Cohort5.Clups.services.UserService;
import com.MIW.Cohort5.Clups.services.dtoConverters.UserDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * This service makes a connection with the user repository.
 */

@Service
public class UserDetailsServiceImpl implements UserService {

    private UserDtoConverter dtoConverter = new UserDtoConverter();

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(
                () -> new UsernameNotFoundException("User " + s + " was not found."));
    }

    @Override
    public List<UserDto> getAll() {

        List<User> models = userRepository.findAll();
        return dtoConverter.toUserDtos(models);
    }

    @Override
    public void saveUser(UserDto userDto) {
        int userCode = makeUserCode(userDto);

        userDto.setUserCode(userCode);

        if (userDto.getUsername() == null || userDto.getUsername() == "") {
            userDto.setUsername("user" + userDto.getUserCode());
        }

        if (userDto.getPassword() == null) {
            userDto.setPassword("pw" + userDto.getUserCode());
        }

        User newUser = dtoConverter.toModel(userDto);

        addUser(newUser);
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

    private Integer makeUserCode(UserDto userDto) {
        int userCode = 0;

        if (userDto.getUserCode() <= 0) {
            userCode = getHighestUserCode() + 1;
        }

        return userCode;
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

