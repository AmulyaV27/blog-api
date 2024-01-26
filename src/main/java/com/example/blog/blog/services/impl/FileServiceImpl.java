package com.example.blog.blog.services.impl;

import com.example.blog.blog.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile image) throws IOException {
        String name = image.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String updatedName = randomId.concat(name.substring(name.lastIndexOf('.')));
        String filePath = path + File.separator + updatedName;
        File parentDir = new File(path);
        if(!parentDir.exists()){
            boolean dirCreated=parentDir.mkdir();
        }
        Files.copy(image.getInputStream(), Paths.get(filePath));
        return updatedName;
    }

    @Override
    public InputStream getImage(String path, String image) throws FileNotFoundException {
        String fullPath=path+File.separator+image;
        return new FileInputStream(fullPath);
    }
}
