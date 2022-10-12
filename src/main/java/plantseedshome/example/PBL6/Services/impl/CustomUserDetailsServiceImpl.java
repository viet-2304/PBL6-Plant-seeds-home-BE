package plantseedshome.example.PBL6.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import plantseedshome.example.PBL6.DAO.entity.Roles;
import plantseedshome.example.PBL6.DAO.entity.User;
import plantseedshome.example.PBL6.DAO.repository.UserRepository;
import plantseedshome.example.PBL6.Services.CustomUserDetailsService;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user= userRepository.findByUserName(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email:" + email));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), mapRolesToAuthorities((Set<Roles>) user.getRoles()));

    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Roles> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}
