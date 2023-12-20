package com.example.smartcontrol.domain.user;

public enum
UserEnum {
    ADMIN("ADMIN"),
    SECURITY("SECURITY"),
    TEACHER("TEACHER");

    private String role;

    UserEnum(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
