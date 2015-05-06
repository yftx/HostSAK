package com.snail.hostseditor.dagger;

import com.snail.hostseditor.App;
import com.snail.hostseditor.core.NetEngine;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yftx on 2/4/15.
 */
@Module(
        library = true,
        includes = AndroidModule.class
)
public class NetModule {
    @Provides
    @Singleton
    public NetEngine provideNetEngine(App app) {
        return new NetEngine(app);
    }
}
