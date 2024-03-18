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
import java.util.Optional;

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


    @GetMapping("/about/{bookID}")
    public String about(Model model,@PathVariable("bookID") Integer bookID){
        BookDTO book=service.findBook(bookID);
        System.out.println(book);
        model.addAttribute("bookDTO",book);
       return "book-about";
    }




    @PostMapping("/search")
    public String search(Model model, @ModelAttribute String search){
        service.serach(search);
        return "menu";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BookDTO dto){
        ResponseEntity.ok( service.create(dto));
        return "adm-menu";
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
