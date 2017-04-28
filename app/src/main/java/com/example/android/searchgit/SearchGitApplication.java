package com.example.android.searchgit;

import android.app.Application;

import com.example.android.searchgit.data.remote.GithubService;
import com.example.android.searchgit.di.AppComponent;
import com.example.android.searchgit.di.AppModule;
import com.example.android.searchgit.di.DaggerAppComponent;

import javax.inject.Inject;

/**
 * Created by yuanyou.
 */

public class SearchGitApplication extends Application {
    private AppComponent appComponent;

    private static SearchGitApplication application;

    public static SearchGitApplication getApplication() {
        return application;
    }

    @Inject
    GithubService githubService;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
