package com.hkouo.rebind.model;

import lombok.Data;

import java.util.List;

@Data
public class ScenarioCharacterForm {
    private Long scenarioIdx;
    private List<ScenarioCharacter> characters;
}
