package com.example.BookShoop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AttachService {


        public String saveToSystem(MultipartFile file) { // mazgi.png
            try {
                File folder = new File("attaches");
                if (!folder.exists()) {
                    folder.mkdir();
                }

                byte[] bytes = file.getBytes();
                Path path = Paths.get("attaches/" + file.getOriginalFilename()); // attaches/mazgi.png
                Files.write(path, bytes);
                return file.getOriginalFilename();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

