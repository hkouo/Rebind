package com.hkouo.rebind.model;

import lombok.Data;

@Data
public class ChapterImage {
    private Long idx;
    private Long chapterIdx;
    private String type; // BACKGROUND, FURNITURE, ENVIRONMENT
    private String imageUrl;
    private int zIndex;
}

