package com.SmartWatch.security;

import com.SmartWatch.entity.UserEntity;
import com.SmartWatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity==null) throw new UsernameNotFoundException("User Not Found with username: " + username);
        return new UserDetailsImpl(userEntity);
    }
}