package com.SmartWatch.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetUserRelationResponse {
    private long id;
    private GetUserResponse patient;
    private GetUserResponse care;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
