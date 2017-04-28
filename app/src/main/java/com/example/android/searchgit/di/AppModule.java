package com.example.android.searchgit.di;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yuanyou.
 */

@Module
public class AppModule {
    private final Application application;

    public AppModule(final Application application) {
        this.application = application;
    }

    @Provides
    public Context providesContext() {
        return application;
    }

}
