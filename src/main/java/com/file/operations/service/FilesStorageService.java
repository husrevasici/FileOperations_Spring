package com.file.operations.service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import com.file.operations.dto.FileDto;
import com.file.operations.entity.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
    public void init();

    public void save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();

    List<File> findAll();


}