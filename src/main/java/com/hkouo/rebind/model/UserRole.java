package com.hkouo.rebind.model;

import lombok.Data;

@Data
public class UserRole {
    private Long idx;
    private Long userIdx;
    private String role;

}
