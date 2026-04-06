package com.servixo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {

    private final String uploadDir = "uploads/";

    public String uploadFile(MultipartFile file) {

        try {
            // create folder if not exists
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // file path
            String filePath = uploadDir + file.getOriginalFilename();

            // save file
            file.transferTo(new File(filePath));

            return filePath;

        } catch (IOException e) {
            throw new RuntimeException("File upload failed");
        }
    }
}