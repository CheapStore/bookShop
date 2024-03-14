package com.example.BookShoop.controller;

import com.example.BookShoop.dto.BookDTO;
import com.example.BookShoop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {


    @Autowired
    private BookService service;

    @GetMapping()
    public String menu(){
        return "menu";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute String search){
        service.serach(search);
        return "menu";
    }

    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody BookDTO dto){
       return ResponseEntity.ok( service.create(dto));
    }


}
