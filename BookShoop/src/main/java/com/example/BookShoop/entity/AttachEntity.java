package com.example.BookShoop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
@Setter
@Getter
@Entity
@Table(name = "attach")
public class AttachEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")

    private String id;

    @Column(name = "attach_path")
    private String path;

    @Column(name = "book_id")
    private Integer bookId;



}
