package com.example.smartcontrol.domain.user;

public enum UserEnum {
    ADMIN("admin"),
    SECURITY("security"),
    TEACHER("teacher");

    private String role;

    UserEnum(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
