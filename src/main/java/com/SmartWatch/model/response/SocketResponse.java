package com.SmartWatch.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocketResponse<T> {
    private String type;
    private T data;

    public SocketResponse(String type, T data) {
        this.type = type;
        this.data = data;
    }
}
