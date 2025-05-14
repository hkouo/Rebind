package com.hkouo.rebind.model;

import lombok.Data;

import java.util.Date;

@Data
public class ScenarioParticipant {
    private Long idx;
    private Long scenarioIdx;
    private Long userIdx;
    private Date joinedAt;
    private Boolean isReady;
    private Boolean isHost;
    private Date lastAccessedAt;
}
