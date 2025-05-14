package com.hkouo.rebind.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ScenarioForm {
    private String title;
    private MultipartFile imageFile;
    private String participantUserIdxList;
}
