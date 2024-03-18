package com.example.BookShoop.repository;

import com.example.BookShoop.entity.BookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.swing.plaf.basic.BasicEditorPaneUI;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<BookEntity,Integer> {


    @Query("from BookEntity b where b.id=?1")
    Optional<BookEntity>search(Integer bookId);

      @Query("FROM BookEntity ORDER BY publicationDate DESC LIMIT 8")
    List<BookEntity>limit();
}
