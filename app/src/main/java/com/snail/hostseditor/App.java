package com.snail.hostseditor;

import android.app.Application;
import android.content.Context;

import com.snail.hostseditor.core.logging.ReleaseLogTree;
import com.snail.hostseditor.dagger.AndroidModule;
import com.snail.hostseditor.dagger.HostsEditorModule;
import com.snail.hostseditor.dagger.NetModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;
import timber.log.Timber;

/**
 * Creates and provides access to Dagger's {@link ObjectGraph} instance.
 */
public class App extends Application {

    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        initLogger();
        buildObjectGraph();
    }

    public void inject(Object target) {
        mObjectGraph.inject(target);
    }

    public <T> T get(Class<T> type) {
        return mObjectGraph.get(type);
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
                new HostsEditorModule()
        );
    }
}
