// FileStorageService.java
package com.hkouo.rebind.service;

import com.hkouo.rebind.mapper.FileMapper;
import com.hkouo.rebind.model.FileMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Service
public class FileStorageService {

    private static final String BASE_UPLOAD_DIR = "./uploads";
    private static final String BASE_URL = "http://localhost:8081/uploads";

    @Autowired
    private FileMapper fileMapper;

    private void uploadFileToDockerFileServer(MultipartFile file, String storedName, String today) throws IOException {
        String targetUrl = "http://localhost:8081/upload?subdir=" + today + "&filename=" + storedName;
        HttpURLConnection connection = (HttpURLConnection) new URL(targetUrl).openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", file.getContentType());

        try (OutputStream os = connection.getOutputStream()) {
            os.write(file.getBytes());
        }

        int responseCode = connection.getResponseCode();
        if (responseCode >= 400) {
            throw new IOException("파일 서버 업로드 실패. 응답 코드: " + responseCode);
        }
    }
}
