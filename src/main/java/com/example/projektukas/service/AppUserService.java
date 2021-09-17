package com.example.projektukas.service;

import com.example.projektukas.model.AppUser;
import com.example.projektukas.repository.AppUserRepository;
import com.example.projektukas.web.dto.AppUserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

//@Service
public interface AppUserService extends UserDetailsService {

    AppUser save(AppUserRegistrationDto registrationDto);

}
