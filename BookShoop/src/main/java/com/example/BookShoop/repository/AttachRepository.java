package com.example.BookShoop.repository;

import com.example.BookShoop.entity.AttachEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AttachRepository extends CrudRepository<AttachEntity,String> {



    Optional<AttachEntity>findByBookId(Integer id);

}
