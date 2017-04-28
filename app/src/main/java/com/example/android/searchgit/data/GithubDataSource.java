package com.example.android.searchgit.data;

import android.util.SparseArray;

import com.example.android.searchgit.data.model.Repo;
import com.example.android.searchgit.data.model.RepoSearchResult;
import com.example.android.searchgit.data.remote.GithubService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yuanyou.
 */
@Singleton
public class GithubDataSource {

    private GithubService githubService;

    private SparseArray<List<Repo>> cachedOrgRepos = new SparseArray<>();
    private boolean isOrgReposCacheDirty = true;

    @Inject
    public GithubDataSource(GithubService githubService) {
        this.githubService = githubService;
    }

    public Observable<List<Repo>> getOrgReposFirstPage() {
        List<Repo> repos = cachedOrgRepos.get(1);
        if (!isOrgReposCacheDirty) {
            return Observable.just(repos);
        }
        cachedOrgRepos.clear();
        return githubService.getOrgRepos("square", "sources")
                .subscribeOn(Schedulers.io())
                .doOnNext(repos1 -> cachedOrgRepos.put(1, repos1))
                .doOnComplete(() -> isOrgReposCacheDirty = false);
    }

    public Observable<List<Repo>> getOrgReposAt(int pageNum) {
        List<Repo> repos = cachedOrgRepos.get(pageNum);
        if (!isOrgReposCacheDirty && !repos.isEmpty()) {
            return Observable.just(repos);
        }
        String url = "https://api.github.com/orgs/" + "square" + "/repos?type=sources&&page=" + pageNum;
        return githubService.getOrgReposPaginate(url)
                .subscribeOn(Schedulers.io())
                .doOnNext(repos1 -> cachedOrgRepos.put(pageNum, repos1))
                .doOnComplete(() -> isOrgReposCacheDirty = false);
    }

    public Observable<List<Repo>> searchRepoFirstPage(String query) {
        return githubService.searchRepos(query)
                .subscribeOn(Schedulers.io())
                .map(RepoSearchResult::getRepos);
    }

    public Observable<List<Repo>> searchRepoPageAt(String query, int pageNum) {
        String url = "https://api.github.com/search/repositories?q=" + query + "?page=" + pageNum;
        return githubService.searchReposPaginate(url)
                .subscribeOn(Schedulers.io())
                .map(RepoSearchResult::getRepos);
    }
}
