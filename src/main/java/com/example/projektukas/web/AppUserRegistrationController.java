package com.example.projektukas.web;

import com.example.projektukas.service.AppUserService;
import com.example.projektukas.web.dto.AppUserRegistrationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("registration")
@AllArgsConstructor
public class AppUserRegistrationController {

    private final AppUserService appUserService;

    //returns view template
    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @ModelAttribute("appUser")
    public AppUserRegistrationDto appUserRegistrationDto(){
        return new AppUserRegistrationDto();
    }

    @PostMapping
    public String registerAppUserAccount(@ModelAttribute("appUser")AppUserRegistrationDto registrationDto){
        appUserService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
