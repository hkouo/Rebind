package com.hkouo.rebind.service;

import com.hkouo.rebind.mapper.ScenarioMapper;
import com.hkouo.rebind.model.Scenario;
import com.hkouo.rebind.model.ScenarioChapterForm;
import com.hkouo.rebind.model.ScenarioCharacter;
import com.hkouo.rebind.model.ScenarioCharacterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ScenarioService {

    @Autowired
    private ScenarioMapper scenarioMapper;

    @Autowired
    private FileUploadService fileUploadService;

    public List<Scenario> getScenariosForUser(Long userIdx, int page, int size) {
        int offset = (page - 1) * size;
        return scenarioMapper.findScenariosForUser(userIdx, size, offset);
    }

    public Long createScenarioStep1(Scenario scenario, Long creatorUserIdx) {
        try {
            if (scenario.getImageFile() != null && !scenario.getImageFile().isEmpty()) {
                String imageUrl = fileUploadService.uploadToExternalServer(scenario.getImageFile(), creatorUserIdx);
                scenario.setImagePath(imageUrl); // DB 저장용
            }
        } catch (Exception e) {
            throw new RuntimeException("이미지 업로드 실패", e);
        }

        scenario.setCreatorUserIdx(creatorUserIdx);
        scenarioMapper.insertScenario(scenario);
        scenarioMapper.insertScenarioParticipantsByUserIds(scenario.getIdx(),  scenario.getParticipantUserIdList());

        // 참여자 등록 생략 가능 (앞에서 구성 완료)
        return scenario.getIdx();
    }


    public void saveScenarioCharacters(ScenarioCharacterForm scenarioCharacterForm) {

        scenarioMapper.insertScenarioCharacters(scenarioCharacterForm);

    }

    public void saveScenarioChapters(ScenarioChapterForm form) {
        scenarioMapper.insertScenarioChapters(form);
    }


}
