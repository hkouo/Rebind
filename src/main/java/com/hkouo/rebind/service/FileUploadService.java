package com.hkouo.rebind.service;

import com.hkouo.rebind.mapper.FileMapper;
import com.hkouo.rebind.model.FileMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class FileUploadService {

    private final FileMapper fileMapper;

    @Value("${file.upload.base-dir}")
    private String baseDir;

    @Value("${file.upload.url-prefix}")
    private String urlPrefix;

    public FileUploadService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public FileMetadata storeFile(MultipartFile multipartFile, Long uploaderUserIdx) throws IOException {
        String datePath = LocalDate.now().toString().replace("-", "/"); // yyyy/MM/dd
        String saveDirPath = baseDir + "/" + datePath;
        File saveDir = new File(saveDirPath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        String originalName = multipartFile.getOriginalFilename();
        String storedName = UUID.randomUUID() + "_" + originalName;
        File saveFile = new File(saveDir, storedName);
        multipartFile.transferTo(saveFile);

        FileMetadata metadata = new FileMetadata();
        metadata.setOriginalName(originalName);
        metadata.setStoredName(storedName);
        metadata.setFilePath(datePath + "/" + storedName);
        metadata.setFileType(multipartFile.getContentType());
        metadata.setFileSize(multipartFile.getSize());
        metadata.setUploaderUserIdx(uploaderUserIdx);

        fileMapper.insertFile(metadata);
        return metadata;
    }

    public String getFileUrl(FileMetadata metadata) {
        return urlPrefix + "/" + metadata.getFilePath();
    }
}
