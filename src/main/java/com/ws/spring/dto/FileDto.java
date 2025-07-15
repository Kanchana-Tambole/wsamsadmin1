package com.ws.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {

    private String fileName;

    private String fileType;

    private long fileSize;

    private String downloadUrl;

    private LocalDateTime uploadedAt;

    private String uploadedBy;
}
