package com.hkouo.rebind.model;

import lombok.Data;

import java.util.List;

@Data
public class ChapterCondition {
    private Long idx;
    private Long chapterIdx;
    private String jsonConditionData; // JSON 형태로 저장

    private List<ChapterEvent> events;
}

