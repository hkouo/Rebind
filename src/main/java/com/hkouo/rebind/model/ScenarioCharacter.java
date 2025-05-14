package com.hkouo.rebind.model;

import lombok.Data;

@Data
public class ScenarioCharacter {
    private Long idx;
    private Long scenarioIdx;
    private String name;
    private String type; // "PC" or "NPC"
    private Long userIdx;
    private Integer hp;
    private Boolean isAssignable;
}
