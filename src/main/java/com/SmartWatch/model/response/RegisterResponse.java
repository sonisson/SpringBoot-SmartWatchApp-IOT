package com.SmartWatch.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private boolean success=true;
    private Data data;

    @Getter
    @Setter
    public static class Data {
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
