package com.dogukanhan.kodgemisi.simplehr.service;



import org.apache.commons.compress.compressors.FileNameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    private String value = "./uploads";

    @Value("${file.acceptedTypes}")
    private String[] acceptedTypes;

    @Autowired
    public FileStorageService() {
        this.fileStorageLocation = Paths.get(value)
                .toAbsolutePath().normalize();
        if(!this.fileStorageLocation.toFile().exists()){
            this.fileStorageLocation.toFile().mkdir();
        }
    }

    public String storeFile(MultipartFile file) throws IOException {
            String ext = getFileExtension(file.getOriginalFilename());
            Arrays.asList(acceptedTypes).forEach(System.out::println);
            if(!Arrays.asList(acceptedTypes).contains(ext)){
                throw new IOException("Unsupported File Type");
            }
            String fileName;
            Path targetLocation;
            do{
                fileName = StringUtils.cleanPath(UUID.randomUUID().toString()+ext);
                targetLocation = this.fileStorageLocation.resolve(fileName);
            }while (targetLocation.toFile().exists());
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
    }

    private String getFileExtension(String name) throws IOException {
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            throw new IOException("File name can't be without an extension.");
        }
        return name.substring(lastIndexOf);
    }


    public Resource loadFileAsResource(String fileName) throws MalformedURLException {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            return resource;
    }
}