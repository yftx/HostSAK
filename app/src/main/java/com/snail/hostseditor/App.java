package com.snail.hostseditor;

import android.app.Application;
import android.content.Context;

import com.hannesdorfmann.mosby.dagger1.Injector;
import com.snail.hostseditor.core.logging.ReleaseLogTree;
import com.snail.hostseditor.dagger.AndroidModule;
import com.snail.hostseditor.dagger.DependModule;
import com.snail.hostseditor.dagger.NetModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;
import timber.log.Timber;

/**
 * Creates and provides access to Dagger's {@link ObjectGraph} instance.
 */
public class App extends Application implements Injector{

    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        buildObjectGraph();
        initLogger();
    }

    //非activity注入对象
    public void inject(Object target) {
        mObjectGraph.inject(target);
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    private void initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ReleaseLogTree());
        }
    }

    private void buildObjectGraph() {
        mObjectGraph = ObjectGraph.create(getModules().toArray());
    }

    private List<Object> getModules() {
        return Arrays.asList(
                new AndroidModule(this),
                new NetModule(),
                new DependModule()
        );
    }

    @Override
    public ObjectGraph getObjectGraph() {
        return mObjectGraph;
    }
}
