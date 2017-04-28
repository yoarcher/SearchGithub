package com.example.android.searchgit.di;

import com.example.android.searchgit.SearchGitApplication;
import com.example.android.searchgit.data.remote.GithubModule;
import com.example.android.searchgit.ui.FeedFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yuanyou.
 */

@Singleton
@Component(modules = {
        AppModule.class,
        GithubModule.class
})
public interface AppComponent {

    void inject(SearchGitApplication searchGitApplication);

    void inject(FeedFragment feedFragment);

}