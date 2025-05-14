package com.hkouo.rebind.model;

import lombok.Data;

import java.util.Date;

@Data
public class FileMetadata {
    private Long idx;
    private String originalName;
    private String storedName;
    private String filePath;
    private String fileType;
    private Long fileSize;
    private Date createdAt;
    private Long uploaderUserIdx;
}
