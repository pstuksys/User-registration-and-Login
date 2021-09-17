package com.example.projektukas.service;

import com.example.projektukas.model.AppUser;
import com.example.projektukas.model.Role;
import com.example.projektukas.repository.AppUserRepository;
import com.example.projektukas.web.dto.AppUserRegistrationDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService{

    private final AppUserRepository appUserRepository;


    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser save(AppUserRegistrationDto registrationDto) {
        AppUser appUser = new AppUser(registrationDto.getUsername(), registrationDto.getEmail(),
                registrationDto.getFirstName(), registrationDto.getLastName(),
                passwordEncoder.encode(registrationDto.getPassword()),
                Arrays.asList(new Role("ROLE_USER")));

        return appUserRepository.save(appUser);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email);
        //Optional<AppUser> appUser = appUserRepository.findByEmail(email);
        if (appUser == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(appUser.getEmail(), appUser.getPassword(), mapRolesToAuthorities(appUser.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }
}
