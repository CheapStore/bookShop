package com.example.BookShoop.repository;

import com.example.BookShoop.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

import javax.swing.plaf.basic.BasicEditorPaneUI;

public interface BookRepository extends CrudRepository<BookEntity,Integer> {
}
