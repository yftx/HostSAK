package com.snail.hostseditor.dagger;

import com.snail.hostseditor.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yftx on 2/4/15.
 */
@Module(
        library = true
)
public class AndroidModule {
    private final App mApp;

    public AndroidModule(App mApp) {
        this.mApp = mApp;
    }

    @Provides
    @Singleton
    App provideApp() {
        return mApp;
    }
}
