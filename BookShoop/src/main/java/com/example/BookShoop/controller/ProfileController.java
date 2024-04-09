package com.example.BookShoop.controller;


import com.example.BookShoop.dto.BookDTO;
import com.example.BookShoop.dto.ProfileDTO;
import com.example.BookShoop.entity.ProfileEntity;
import com.example.BookShoop.enums.ProfileRole;
import com.example.BookShoop.service.BookService;
import com.example.BookShoop.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService service;

    @Autowired
    private BookService bookService;
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
        if (profile == null) {
            model.addAttribute("profile", new ProfileDTO());
            return "addProfile";
        } else {
            if (profile.getRole().equals(ProfileRole.ADMIN.name())) {
                return "adm-menu";
            }
            List<BookDTO> list= bookService.getAll();
            model.addAttribute("bookList",list);
            return "menu";
        }

    }

    @PostMapping("/save")
    public String create(Model model, @ModelAttribute ProfileDTO dto) {
             service.create(dto);
             List<BookDTO> list= bookService.getAll();
            model.addAttribute("bookList",list);
            return "menu";
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

       service.deleteProfile(profileID);
        List<ProfileDTO> list = service.getAll();
        model.addAttribute("list", list);
        return "adm-user-list";
    }

    @GetMapping("/go-to-edit/{profileID}")
    public String save(Model model, @PathVariable("profileID") Integer profileID) {
        ProfileDTO profile = service.findProfile(profileID);
        model.addAttribute("profile", profile);
        return "update-profile";
    }

    @PostMapping("/update/{profileId}")
    public String edit(Model model, @PathVariable("profileId") Integer profileId,
                       @ModelAttribute ProfileDTO profileDTO) {

        service.update(profileId, profileDTO);
        List<ProfileDTO> list = service.getAll();
        model.addAttribute("list", list);
        return "adm-user-list";
    }
}

