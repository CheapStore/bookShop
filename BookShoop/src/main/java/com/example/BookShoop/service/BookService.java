package com.example.BookShoop.service;

import com.example.BookShoop.dto.BookDTO;
import com.example.BookShoop.entity.AttachEntity;
import com.example.BookShoop.entity.BookEntity;
import com.example.BookShoop.repository.AttachRepository;
import com.example.BookShoop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {


    @Autowired
    private BookRepository repository;

    @Autowired
    private AttachRepository attachRepository;

    public void serach(String search) {

    }

    public Object create(BookDTO dto) {

        BookEntity entity = new BookEntity();
        entity.setName(dto.getName());
        entity.setAuthor(dto.getAuthor());
        entity.setAttach_id(dto.getAttachID());
        entity.setPublicationDate(dto.getPublicationDate());
        entity.setWrittenDate(dto.getWritterDate());
        entity.setDescription(dto.getDescription());
        entity.setLanguage(dto.getLanguage());
        entity.setPrice(dto.getPrice());
        repository.save(entity);
        AttachEntity attach=new AttachEntity();
        attach.setPath(dto.getAttachID());
        attach.setBookId(entity.getId());
        attachRepository.save(attach);
        entity.setAttach(attach);
        repository.save(entity);
        return true;
    }

    public List<BookDTO> getAll() {
        List<BookEntity> all = repository.limit();
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
        dto.setPublicationDate(entity.getPublicationDate());
        dto.setWritterDate(entity.getWrittenDate());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public BookDTO findBook(Integer bookID) {
        Optional<BookEntity> search = repository.search(bookID);
        return dto(search.get());
    }
}
