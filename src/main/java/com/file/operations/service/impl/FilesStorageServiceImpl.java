package com.file.operations.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import com.file.operations.api.FilesController;
import com.file.operations.dto.FileDto;
import com.file.operations.repository.FileRepository;
import com.file.operations.service.FilesStorageService;
import com.file.operations.entity.File;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
    private final Path root = Paths.get("uploads");
    private final FileRepository fileRepository;

    public FilesStorageServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            if (!file.getOriginalFilename().equals(""))
                Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));

            File dbFileObject = new File(
                    file.getOriginalFilename().toString(),
                    root.toUri().toString(),
                    file.getContentType(),
                    file.getSize(), LocalDateTime.now()

            );
            fileRepository.save(dbFileObject);

            System.out.println("file added successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }


    @Override
    public List<File> findAll() {
        return fileRepository.findAll();
    }

    @Override
    public Resource load(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {
        // FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }


}