package com.example.BookShoop.service;


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
        if (Objects.equals(dto.getEmail(), "") || Objects.equals(dto.getName(), "") || Objects.equals(dto.getSurname(), "")) {
            return false;
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setRole(ProfileRole.USER);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        repository.save(entity);
        return true;
    }

    public ProfileDTO login(ProfileDTO dto) {
        Optional<ProfileEntity> optional = repository.findByNameAndEmail(dto.getName(), dto.getEmail());
        if (optional.isEmpty()) {
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setEmail(null);
            return profileDTO;
        }
        ProfileEntity entity = optional.get();
        ProfileDTO dto1 = new ProfileDTO();
        dto1.setEmail(entity.getEmail());
        dto1.setName(entity.getName());
        dto1.setSurname(entity.getSurname());
        dto1.setRole(entity.getRole().name());
        return dto1;


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
        dto.setSurname(entity.getSurname());
        dto.setName(entity.getName());
        return dto;
    }
}
