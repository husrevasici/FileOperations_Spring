package com.file.operations.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor

public class FileDto {
    private String fileName;
    private String fileUri;
    private String fileContentName;
    private Long fileSize;
    private LocalDate date;


    public FileDto(String fileName, String fileUri, String fileContentName, Long fileSize, LocalDate date) {
        this.fileName = fileName;
        this.fileUri = fileUri;
        this.fileContentName = fileContentName;
        this.fileSize = fileSize;
    }

}