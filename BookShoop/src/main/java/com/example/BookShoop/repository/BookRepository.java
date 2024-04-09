package com.example.BookShoop.repository;

import com.example.BookShoop.entity.BookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.swing.plaf.basic.BasicEditorPaneUI;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<BookEntity, Integer> {


    @Query("from BookEntity b where b.id=?1")
    Optional<BookEntity> search(Integer bookId);

    @Query("FROM BookEntity ORDER BY publicationDate DESC LIMIT 8")
    List<BookEntity> limit();

    @Query("from BookEntity b where lower(b.category)=?1")
    List<BookEntity> findCategory(String substring);


    @Query("from BookEntity e where lower(e.name) like  %:search%")
    List<BookEntity> findBook(@Param("search") String search);


}
