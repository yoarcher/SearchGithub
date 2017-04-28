package com.example.android.searchgit.ui;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.example.android.searchgit.data.GithubDataSource;
import com.example.android.searchgit.data.model.Repo;
import com.example.android.searchgit.ui.common.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by yuanyou.
 */

public class FeedViewModel extends BaseViewModel {

    public final ObservableField<FeedAdapter> adapter = new ObservableField<>();
    public final ObservableBoolean showEmptyMessage = new ObservableBoolean(false);

    private final GithubDataSource dataSource;

    @Inject
    FeedViewModel(GithubDataSource dataSource) {
        this.dataSource = dataSource;
        adapter.set(new FeedAdapter());
    }

    public Observable<List<Repo>> getReposFirstPage() {
        return dataSource.getOrgReposFirstPage();
    }

    public Observable<List<Repo>> getSearchResultFirstPage(String query) {
        return dataSource.searchRepoFirstPage(query);
    }

    public Observable<List<Repo>> getReposNext(int pageNum) {
        return dataSource.getOrgReposAt(pageNum);
    }

    public Observable<List<Repo>> getSearchResultNext(String query, int pageNum) {
        return dataSource.searchRepoPageAt(query, pageNum);
    }

    public void replaceData(List<Repo> repos) {
        showEmptyMessage.set(repos.isEmpty());
        adapter.get().replaceData(repos);
    }

    public void appendData(List<Repo> repos) {
        adapter.get().appendData(repos);
    }
}
