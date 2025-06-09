package com.hkouo.rebind.service;

import com.hkouo.rebind.mapper.FileMapper;
import com.hkouo.rebind.model.FileMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


    public String uploadToExternalServer(MultipartFile file, Long uploaderUserIdx) throws IOException {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String storedName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        String uploadUrl = "http://localhost:8082/upload?subdir=" + today + "&filename=" + storedName;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(file.getContentType()));

        HttpEntity<byte[]> requestEntity = new HttpEntity<>(file.getBytes(), headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                uploadUrl, HttpMethod.PUT, requestEntity, String.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IOException("업로드 실패: " + response.getStatusCode());
        }

        String fileUrl = "http://localhost:8081/uploads/" + today + "/" + storedName;

        // ✅ file_metadata 테이블에 저장
        FileMetadata metadata = new FileMetadata();
        metadata.setOriginalName(file.getOriginalFilename());
        metadata.setStoredName(storedName);
        metadata.setFilePath(today);
        metadata.setFileType(file.getContentType());
        metadata.setFileSize(file.getSize());
        metadata.setUploaderUserIdx(uploaderUserIdx);

        fileMapper.insertFile(metadata);

        return fileUrl;


    }



}
