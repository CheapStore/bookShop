package com.example.BookShoop.dto;

import com.example.BookShoop.enums.BookLanguage;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class BookDTO {
    private String name;

    private Integer id;
    private String author;
    private Double price;
    private BookLanguage language;
    private  String attachID;
    private LocalDateTime createdDate;
    private String description;

}
