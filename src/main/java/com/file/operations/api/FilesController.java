package com.file.operations.api;

import com.file.operations.dto.FileDto;
import com.file.operations.entity.File;
import com.file.operations.messages.ResponseMessage;
import com.file.operations.service.FilesStorageService;
import com.file.operations.service.impl.FilesStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller

public class FilesController {
    @Autowired
    FilesStorageService storageService;

    @Autowired
    FilesStorageServiceImpl fileStorageServiceImpl;
    public static String MESSAGES = "";


    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        try {

            if (
                    (file.getContentType().equals("image/png")) |
                            (file.getContentType().equals("image/jpeg")) |
                            (file.getContentType().equals("application/msword")) |
                            (file.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) |
                            (file.getContentType().equals("application/pdf")) |
                            (file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))


            ) {

                storageService.save(file);
                MESSAGES = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(MESSAGES));


            } else {
                MESSAGES = "the file format is not suitable: " + file.getOriginalFilename();
                throw new RuntimeException("the file format is not suitable");
            }

        } catch (
                Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(MESSAGES));
        }

    }

    @GetMapping("/files")

    public ResponseEntity<List<File>> getAll() {
        List<File> fileListAll = fileStorageServiceImpl.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(fileListAll);
    }


}