package com.hkouo.rebind.model;

import lombok.Data;

@Data
public class ChapterItem {
    private Long idx;
    private Long chapterIdx;
    private String name;
    private String description;
    private String imageUrl;
}

