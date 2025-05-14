package com.hkouo.rebind.model;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class User {
    private Long idx;
    private String uniqueName;
    private String userId;
    private String password;
    private String email;
    private String nickname;
    private Boolean isActive;
    private Integer loginFailCount;
    private Date lastLoginAt;
    private Date createdAt;
    private Date updatedAt;

    private List<UserRole> roles; // 조인용

}
