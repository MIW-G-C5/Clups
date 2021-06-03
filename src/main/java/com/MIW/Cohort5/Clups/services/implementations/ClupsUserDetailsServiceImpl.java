package com.MIW.Cohort5.Clups.services.implementations;

import com.MIW.Cohort5.Clups.dtos.UserDto;
import com.MIW.Cohort5.Clups.model.ClupsUser;
import com.MIW.Cohort5.Clups.repository.ClupsUserRepository;
import com.MIW.Cohort5.Clups.services.dtoConverters.UserDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
public class ClupsUserDetailsServiceImpl implements UserDetailsService {

    private UserDtoConverter dtoConverter = new UserDtoConverter();

    private final ClupsUserRepository clupsUserRepository;

    @Autowired
    public ClupsUserDetailsServiceImpl(ClupsUserRepository clupsUserRepository) {
        this.clupsUserRepository = clupsUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return clupsUserRepository.findByUsername(s).orElseThrow(
                () -> new UsernameNotFoundException("User " + s + " was not found."));
    }

    public List<UserDto> getAll() {

        List<ClupsUser> models = clupsUserRepository.findAll();
        return dtoConverter.toUserDtos(models);
    }

    public void addUser(String username, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);

        ClupsUser user = new ClupsUser();
        user.setUsername(username);
        user.setPassword(encodedPassword);

        clupsUserRepository.save(user);
    }

}
