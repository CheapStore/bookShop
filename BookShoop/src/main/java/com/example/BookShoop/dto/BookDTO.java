package com.example.BookShoop.dto;

import com.example.BookShoop.enums.BookLanguage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter

public class BookDTO {
    private String name;
    private String author;
    private Double price;
    private BookLanguage language;
    private  String attachID;
    private LocalDateTime createdDate;

}
