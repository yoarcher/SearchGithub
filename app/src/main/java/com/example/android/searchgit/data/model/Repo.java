package com.example.android.searchgit.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by yuanyou.
 */

public class Repo {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("full_name")
    String fullName;

    @SerializedName("owner")
    Actor actor;

    @SerializedName("updated_at")
    Date updatedAt;

    @SerializedName("forks_count")
    int forksCount;

    @SerializedName("stargazers_count")
    int starsCount;

    @SerializedName("description")
    String description;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public int getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(int starsCount) {
        this.starsCount = starsCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
