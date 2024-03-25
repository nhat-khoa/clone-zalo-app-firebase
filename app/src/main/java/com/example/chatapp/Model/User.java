package com.example.chatapp.Model;

public class User {
    private String id;
    private String name;
    private String profile;
    private String status;

    public User(String id, String name, String profile, String status) {
        this.id = id;
        this.name = name;
        this.profile = profile;
        this.status = status;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
