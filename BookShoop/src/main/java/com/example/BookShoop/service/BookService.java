package com.example.BookShoop.service;

import com.example.BookShoop.dto.BookDTO;
import com.example.BookShoop.entity.BookEntity;
import com.example.BookShoop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {


    @Autowired
    private BookRepository repository;

    public void serach(String search) {

    }

    public Object create(BookDTO dto) {
        BookEntity entity = new BookEntity();
        entity.setName(dto.getName());
        entity.setAuthor(dto.getAuthor());
        entity.setAttach_id(dto.getAttachID());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setLanguage(dto.getLanguage());
        entity.setPrice(dto.getPrice());
        repository.save(entity);
        return true;
    }

    public List<BookDTO> getAll() {
        Iterable<BookEntity> all = repository.findAll();
        List<BookDTO> list = new ArrayList<>();
        for (BookEntity entity : all) {
            list.add(dto(entity));
        }
        return list;
    }

    private BookDTO dto(BookEntity entity) {
        BookDTO dto = new BookDTO();
        dto.setId(entity.getId());
        dto.setAttachID(entity.getAttach_id());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setLanguage(entity.getLanguage());
        dto.setAuthor(entity.getAuthor());
        dto.setPrice(entity.getPrice());
        return dto;
    }
}
