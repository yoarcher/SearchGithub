package com.example.android.searchgit.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yuanyou.
 */

public class RepoSearchResult {
    @SerializedName("items")
    List<Repo> repos;

    public List<Repo> getRepos() {
        return repos;
    }

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
    }
}
