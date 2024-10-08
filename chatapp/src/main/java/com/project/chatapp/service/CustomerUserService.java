package com.project.chatapp.service;

import com.project.chatapp.modal.User;
import com.project.chatapp.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUserService  implements UserDetailsService {

    private UserRepository userRepository;

    public CustomerUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
      if (user == null) {
          throw new UsernameNotFoundException("User not found with username " +username );
      }

        List<GrantedAuthority> authorities = new ArrayList<>();
      return new org.springframework.security.core.userdetails.User(user.getEmail() , user.getPassword() , authorities);

    }
}
