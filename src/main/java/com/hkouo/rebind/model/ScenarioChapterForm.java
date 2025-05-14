package com.hkouo.rebind.model;

import lombok.Data;

import java.util.List;

@Data
public class ScenarioChapterForm {
    private Long scenarioIdx;
    private List<ScenarioChapter> chapters;
}


