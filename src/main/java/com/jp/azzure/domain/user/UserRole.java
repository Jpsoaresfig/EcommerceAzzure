package com.jp.azzure.domain.user;

public enum UserRole {// Enum for decide user role
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
