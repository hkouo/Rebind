package com.hkouo.rebind.mapper;

import com.hkouo.rebind.model.Scenario;
import com.hkouo.rebind.model.ScenarioChapterForm;
import com.hkouo.rebind.model.ScenarioCharacter;
import com.hkouo.rebind.model.ScenarioCharacterForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScenarioMapper {
    List<Scenario> findScenariosForUser(@Param("userIdx") Long userIdx,
                                        @Param("limit") int limit,
                                        @Param("offset") int offset);

    void insertScenario(Scenario scenario);

    void insertScenarioParticipants(@Param("scenarioIdx") Long scenarioIdx,
                                    @Param("participants") List<Long> participantUserIdxList);

    void insertScenarioCharacter(ScenarioCharacter character);
    void insertScenarioCharacters(ScenarioCharacterForm form);

    void insertScenarioChapters(ScenarioChapterForm form);

}
