package com.example.BookShoop.entity;

import com.example.BookShoop.enums.BookLanguage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "book_name")
    private String name;

    @Column(name = "book_author")
    private String author;
    @Column(name = "book_price")
    private Double price;


    @Column(name = "book_language")
    @Enumerated(EnumType.STRING)
    private BookLanguage language;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attach",referencedColumnName = "id")
    private AttachEntity attach;

    @Column(name = "attach_id",insertable=false, updatable=false)
    private String attach_id;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;




}
