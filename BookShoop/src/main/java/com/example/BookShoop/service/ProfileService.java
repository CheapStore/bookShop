package com.example.BookShoop.service;


import com.example.BookShoop.dto.BookDTO;
import com.example.BookShoop.dto.ProfileDTO;
import com.example.BookShoop.entity.ProfileEntity;
import com.example.BookShoop.enums.ProfileRole;
import com.example.BookShoop.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository repository;

    public boolean create(ProfileDTO dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setRole(ProfileRole.USER);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        repository.save(entity);
        return true;
    }

    public ProfileDTO login(ProfileDTO dto) {
        Optional<ProfileEntity> optional = repository.findByPasswordAndEmail(dto.getPassword(), dto.getEmail());
        return optional.map(this::dto).orElse(null);

    }

    public List<ProfileDTO> getAll() {
        Iterable<ProfileEntity> all = repository.findAll();
        List<ProfileDTO> list = new ArrayList<>();
        for (ProfileEntity profileEntity : all) {
            list.add(dto(profileEntity));
        }
        return list;


    }


    public ProfileDTO dto(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setEmail(entity.getEmail());
        dto.setId(entity.getId());
        dto.setRole(entity.getRole().name());
        dto.setPassword(entity.getPassword());
        dto.setSurname(entity.getSurname());
        dto.setName(entity.getName());
        return dto;
    }

    public ProfileDTO findProfile(Integer profileID) {
        Optional<ProfileEntity> optional = repository.findById(profileID);
        ProfileEntity profileEntity = optional.get();
       return dto(profileEntity);
    }


    public void update(Integer profileId, ProfileDTO dto) {
        Optional<ProfileEntity> optional = repository.findById(profileId);
        ProfileEntity entity = optional.get();
      entity.setPassword(dto.getPassword());
      entity.setEmail(dto.getEmail());
      entity.setRole(ProfileRole.valueOf(dto.getRole()));
      entity.setSurname(dto.getSurname());
      repository.save(entity);
    }

    public void deleteProfile(Integer profileID) {
        repository.deleteById(profileID);
    }
}
