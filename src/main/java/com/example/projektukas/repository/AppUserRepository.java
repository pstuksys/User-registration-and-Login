package com.example.projektukas.repository;

import com.example.projektukas.model.AppUser;
import com.example.projektukas.web.dto.AppUserRegistrationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    AppUser findByEmail(String email);
}
