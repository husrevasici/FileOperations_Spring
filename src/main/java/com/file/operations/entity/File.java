package com.file.operations.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "File")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Name", length = 500)
    private String fileName;

    @Column(name = "Uri", length = 250)
    private String fileUri;

    @Column(name = "ContentName", length = 300)
    private String fileContentName;

    @Column(name = "Size", length = 500)
    private Long fileSize;

    @Column(name = "date")
    private LocalDateTime date;

    public File(String fileName, String fileUri, String fileContentName, long fileSize, LocalDateTime date) {
        this.fileName = fileName;
        this.fileUri = fileUri;
        this.fileContentName = fileContentName;
        this.fileSize = fileSize;
        this.date = date;
    }
}
