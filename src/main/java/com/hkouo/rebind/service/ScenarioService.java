package com.hkouo.rebind.service;

import com.hkouo.rebind.mapper.ScenarioMapper;
import com.hkouo.rebind.model.Scenario;
import com.hkouo.rebind.model.ScenarioChapterForm;
import com.hkouo.rebind.model.ScenarioCharacter;
import com.hkouo.rebind.model.ScenarioCharacterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenarioService {

    @Autowired
    private ScenarioMapper scenarioMapper;

    public List<Scenario> getScenariosForUser(Long userIdx, int page, int size) {
        int offset = (page - 1) * size;
        return scenarioMapper.findScenariosForUser(userIdx, size, offset);
    }

    public Long createScenarioStep1(Scenario scenario, Long creatorUserIdx) {
        scenario.setCreatorUserIdx(creatorUserIdx);
        scenarioMapper.insertScenario(scenario);

        // 참가자 등록
        if (scenario.getParticipantUserIdxList() != null && !scenario.getParticipantUserIdxList().isEmpty()) {
            scenarioMapper.insertScenarioParticipants(scenario.getIdx(), scenario.getParticipantUserIdxList());
        }

        return scenario.getIdx();
    }

    public void saveScenarioCharacters(ScenarioCharacterForm scenarioCharacterForm) {

        scenarioMapper.insertScenarioCharacters(scenarioCharacterForm);

    }

    public void saveScenarioChapters(ScenarioChapterForm form) {
        scenarioMapper.insertScenarioChapters(form);
    }


}
