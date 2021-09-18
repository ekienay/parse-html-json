package com.def.entity;

public class Hero {
    private String name;
    private String avatarImageName;

    public Hero() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarImageName() {
        return avatarImageName;
    }

    public void setAvatarImageName(String avatarImageName) {
        this.avatarImageName = avatarImageName;
    }

    @Override
    public String toString() {
        return "Hero name: " + name+ " || " + " Avatar image filename: " + avatarImageName;
    }
}
