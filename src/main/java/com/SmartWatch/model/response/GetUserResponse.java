package com.SmartWatch.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetUserResponse {
    private long id;
    private String username;
    private String name;
    private String role;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private boolean isActive;
}
