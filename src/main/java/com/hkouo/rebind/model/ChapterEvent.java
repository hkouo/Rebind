package com.hkouo.rebind.model;

import lombok.Data;

@Data
public class ChapterEvent {
    private Long idx;
    private Long conditionIdx;
    private String eventType; // SHOW_MESSAGE, GAIN_ITEM 등
    private String eventData; // JSON 형태
    private int eventOrder;
}

