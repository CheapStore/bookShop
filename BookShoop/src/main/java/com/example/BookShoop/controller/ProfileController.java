package com.example.BookShoop.controller;


import com.example.BookShoop.dto.ProfileDTO;
import com.example.BookShoop.entity.ProfileEntity;
import com.example.BookShoop.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService service;

    @GetMapping("/create")
    public String goToLoginPage(Model model) {
        model.addAttribute("profile", new ProfileDTO());
        return "addProfile";
    }


    @GetMapping("/chesk")
    public String chesk(Model model) {
        model.addAttribute("profile", new ProfileDTO());
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute ProfileDTO dto) {
        ProfileDTO profile = service.login(dto);
        model.addAttribute("profile", profile);
        return "menu-login";
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


    @GetMapping("/adm/menu")
    public String menu(Model model) {
        return "adm-menu";
    }

    @GetMapping("/adm/userlist")
    public String userList(Model model) {
        List<ProfileDTO> list = service.getAll();
        model.addAttribute("list", list);
        return "adm-user-list";
    }

    @GetMapping("/go-to-delete/{profileID}")
    public String delete(Model model, @PathVariable("profileID") Integer profileID) {
        List<ProfileDTO> list = service.getAll();
        for (ProfileDTO dto : list) {
            if (dto.getId().equals(profileID)) {
                list.remove(dto);
                model.addAttribute("adm-user-list", list);
            }

        }
        return "adm-user-list";
    }

    @GetMapping("/go-to-edit/{profileID}")
    public String save(Model model, @PathVariable("profileID") Integer profileID) {
        return "adm-user-list";
    }
}

