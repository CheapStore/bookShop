package com.example.BookShoop.service;


import com.example.BookShoop.dto.ProfileDTO;
import com.example.BookShoop.entity.ProfileEntity;
import com.example.BookShoop.enums.ProfileRole;
import com.example.BookShoop.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository repository;

    public boolean create(ProfileDTO dto) {
        if (Objects.equals(dto.getEmail(), "") || Objects.equals(dto.getName(), "") || Objects.equals(dto.getSurname(), "")){
            return false;
        }
        ProfileEntity entity=new ProfileEntity();
        entity.setRole(ProfileRole.USER);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        repository.save(entity);
        return true;
    }

    public String login(ProfileDTO dto) {
        Optional<ProfileEntity> optional = repository.findByNameAndEmail(dto.getName(), dto.getEmail());
        if (optional.isEmpty()){
            return "yo`q";
        }
    }
}
