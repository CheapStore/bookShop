package com.example.BookShoop.repository;


import com.example.BookShoop.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity,Integer> {


    @Query("from ProfileEntity p where p.password=?1 and p.email=?2")
    Optional<ProfileEntity>findByPasswordAndEmail(String password,String email);
}
