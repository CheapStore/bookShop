package com.example.BookShoop.service;

import com.example.BookShoop.dto.BookDTO;
import com.example.BookShoop.entity.AttachEntity;
import com.example.BookShoop.entity.BookEntity;
import com.example.BookShoop.repository.AttachRepository;
import com.example.BookShoop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {


    @Autowired
    private BookRepository repository;

    @Autowired
    private AttachRepository attachRepository;

    public List<BookDTO> serach(String search) {
        List<BookEntity>resault=repository.findBook(search.toLowerCase());
        List<BookDTO> list = new ArrayList<>();
        for (BookEntity entity : resault) {
            list.add(dto(entity));
        }
        return list;

    }

    public boolean create(BookDTO dto) {
        if (Objects.equals(dto.getName(), "") || Objects.equals(dto.getAttachID(), "") || Objects.equals(dto.getDescription(), "") || Objects.equals(dto.getAuthor(), "") || Objects.equals(dto.getPrice(), null)) {
            return false;
        }
        BookEntity entity = new BookEntity();
        entity.setName(dto.getName());
        entity.setAuthor(dto.getAuthor());
        entity.setAttach_id(dto.getAttachID());
        entity.setPublicationDate(dto.getPublicationDate());
        entity.setWrittenDate(dto.getWritterDate());
        entity.setCategory(dto.getCategory());
        entity.setDescription(dto.getDescription());
        entity.setLanguage(dto.getLanguage());
        entity.setPrice(dto.getPrice());
        repository.save(entity);
        AttachEntity attach = new AttachEntity();
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
        Optional<AttachEntity> optional = attachRepository.findByBookId(entity.getId());
        if (optional.isPresent()) {
            dto.setAttachID(optional.get().getPath());
        }

        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setLanguage(entity.getLanguage());
        dto.setAuthor(entity.getAuthor());
        dto.setPublicationDate(entity.getPublicationDate());
        dto.setWritterDate(entity.getWrittenDate());
        dto.setCategory(entity.getCategory());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public BookDTO findBook(Integer bookID) {
        Optional<BookEntity> search = repository.search(bookID);
        return dto(search.get());
    }

    public void edite(Integer bookId, BookDTO bookDTO) {
        Optional<BookEntity> optional = repository.findById(bookId);
        BookEntity entity = optional.get();
        entity.setCategory(bookDTO.getCategory());
        entity.setPrice(bookDTO.getPrice());
        entity.setName(bookDTO.getName());
        entity.setAuthor(bookDTO.getAuthor());
        entity.setLanguage(bookDTO.getLanguage());
        entity.setDescription(bookDTO.getDescription());
        entity.setWrittenDate(bookDTO.getWritterDate());
        entity.setPublicationDate(bookDTO.getPublicationDate());
        repository.save(entity);

    }

    public List<BookDTO> findCategory(String bookName) {

        List<BookEntity> list = repository.findCategory(bookName.toLowerCase().substring(0, bookName.length() - 3));
        List<BookDTO>dtoList=new ArrayList<>();
        for (BookEntity entity : list) {
           dtoList.add(dto(entity));
        }
        return  dtoList;
    }
}
