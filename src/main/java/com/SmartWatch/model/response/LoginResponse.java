package com.SmartWatch.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoginResponse {
    private boolean success=true;
    private Data data;
    private String timestamp = LocalDateTime.now().toString();

    @Getter
    @Setter
    public static class Data {
        private String token;
        private User user;

        @Getter
        @Setter
        public static class User {
            private long id;
            private String username;
            private String name;
            private String role;
        }
    }
}
