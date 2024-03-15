package com.example.BookShoop.controller;

import com.example.BookShoop.dto.BookDTO;
import com.example.BookShoop.dto.ProfileDTO;
import com.example.BookShoop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {


    @Autowired
    private BookService service;

    @GetMapping("/menu")
    public String menu(Model model){
       List<BookDTO> list= service.getAll();

       model.addAttribute("bookList",list);
       return "menu";
    }







    @PostMapping("/search")
    public String search(Model model, @ModelAttribute String search){
        service.serach(search);
        return "menu";
    }

    @PostMapping("/save")
    public ResponseEntity<?>ave(@ModelAttribute BookDTO dto){
        System.out.println("dto = " + dto);
        return ResponseEntity.ok( service.create(dto));
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("book",new BookDTO());
        return "book-create";
    }


    @GetMapping("/adm/bookList")
    public String userList(Model model) {
        List<BookDTO> list = service.getAll();
        model.addAttribute("bookList",list);
        return "adm-book-list";
    }


}
