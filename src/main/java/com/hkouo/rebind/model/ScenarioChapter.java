package com.hkouo.rebind.model;

import lombok.Data;

import java.util.List;

@Data
public class ScenarioChapter {
    private Long idx;
    private Long scenarioIdx;
    private int chapterOrder;
    private String title;
    private String introScript;
    private String endingScript;
    private Boolean enableChat;
    private Boolean enablePhone;
    private Boolean enableSearch;
    private Long nextChapterIdx;
    private List<ChapterImage> images;
    private List<ChapterItem> items;
    private List<ChapterCondition> conditions;
}


