package com.hkouo.rebind.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
public class Scenario {
    private Long idx;
    private String title;
    private Long creatorUserIdx;
    private Long gameMasterIdx;
    private String gameStatus;
    private Date createdAt;
    private Date updatedAt;

    private String imagePath;
    private ScenarioParticipant participant; // 로그인한 사용자와의 관계 정보
    private List<Long> participantUserIdxList;
    private List<String> participantUserIdList;


    private transient MultipartFile imageFile;
    private Long fileMetadataIdx;


}

