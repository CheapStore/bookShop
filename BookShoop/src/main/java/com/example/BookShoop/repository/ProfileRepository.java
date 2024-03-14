package com.example.BookShoop.repository;


import com.example.BookShoop.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity,Integer> {


    Optional<ProfileEntity>findByNameAndEmail(String name,String email);
}
