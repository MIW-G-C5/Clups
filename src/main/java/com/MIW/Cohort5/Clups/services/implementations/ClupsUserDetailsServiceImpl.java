package com.MIW.Cohort5.Clups.services.implementations;

import com.MIW.Cohort5.Clups.repository.ClupsUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * This service makes a connection with the user repository.
 */

@Service
public class ClupsUserDetailsServiceImpl implements UserDetailsService {

    ClupsUserRepository clupsUserRepository;

    public ClupsUserDetailsServiceImpl(ClupsUserRepository clupsUserRepository) {
        this.clupsUserRepository = clupsUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return clupsUserRepository.findByUsername(s).orElseThrow(
                () -> new UsernameNotFoundException("User " + s + " was not found."));
    }

}
