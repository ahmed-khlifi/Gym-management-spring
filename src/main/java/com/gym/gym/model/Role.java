package com.gym.gym.model;

public enum Role {
    ADMIN, USER, COACH;

    public String getName() {
        return this.name(); // Returns the enum's name as a string (e.g., "ADMIN", "USER", "COACH")
    }
}