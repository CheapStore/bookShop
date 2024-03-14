package com.example.BookShoop.controller;


import com.example.BookShoop.dto.ProfileDTO;
import com.example.BookShoop.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService service;

    @GetMapping("/create")
    public String goToLoginPage(Model model){
        model.addAttribute("profile",new ProfileDTO());
        return "addProfile";
    }


    @GetMapping("/chesk")
    public String chesk(Model model){
        model.addAttribute("profile",new ProfileDTO());
        return "login";
    }

    @GetMapping("/login")
    public String login(Model model,@ModelAttribute ProfileDTO dto){
        service.login(dto);
        return "menu";
    }
    @PostMapping("/save")
    public String create(Model model, @ModelAttribute ProfileDTO dto) {
        boolean b = service.create(dto);
        if (b) {
            return "menu";
        } else {
            model.addAttribute("profile", new ProfileDTO());
            return "addProfile";
        }
    }



    @PostMapping("/adm/menu")
    public String menu(Model model) {
        return "menu";

    }


    }



