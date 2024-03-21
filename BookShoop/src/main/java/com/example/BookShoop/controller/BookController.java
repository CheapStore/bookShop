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
        model.addAttribute("bookDTO",book);
       return "book-about";
    }




    @GetMapping("/search/{searchText}")
    public String search(Model model, @RequestParam String searchText){
        service.serach(searchText);
        model.addAttribute("text",searchText);
        return "search-text";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BookDTO dto){
        ResponseEntity.ok( service.create(dto));
        return "adm-menu";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("book",new BookDTO());
        model.addAttribute("isEdit", false);
        return "book-create";
    }


    @GetMapping("/adm/bookList")
    public String userList(Model model) {
        List<BookDTO> list = service.getAll();
        model.addAttribute("bookList",list);
        return "adm-book-list";
    }

    @GetMapping("/category/{bookName}")
    public String category(Model model,@PathVariable("bookName") String bookName){
        return "menu";
    }


    @GetMapping("/go-to-edit/{bookId}")
    public String save(Model model, @PathVariable("bookId") Integer bookId) {
        BookDTO  dto= service.findBook(bookId);
        if (dto.getId()==null) {
            return "404";
        }
        model.addAttribute("book", dto);
        model.addAttribute("isEdit", true);
        return "book-create";
    }

    @PostMapping("/edite/{bookId}")
    public String edit(Model model, @PathVariable("bookId") Integer bookId,
                       @ModelAttribute BookDTO bookDTO) {
        service.edite(bookId,bookDTO);
        return "adm-book-list";
    }

}
