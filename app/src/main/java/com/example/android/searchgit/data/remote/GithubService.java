package com.example.android.searchgit.data.remote;

import com.example.android.searchgit.data.model.Repo;
import com.example.android.searchgit.data.model.RepoSearchResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by yuanyou.
 */

public interface GithubService {

    @GET("orgs/{org_name}/repos")
    Observable<List<Repo>> getOrgRepos(@Path("org_name") String orgName, @Query("type") String type);

    @GET
    Observable<List<Repo>> getOrgReposPaginate(@Url String url);

    @GET("search/repositories")
    Observable<RepoSearchResult> searchRepos(@Query("q") String query);

    @GET
    Observable<RepoSearchResult> searchReposPaginate(@Url String url);

}
