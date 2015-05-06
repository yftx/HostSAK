package com.snail.hostseditor.dagger;

import com.snail.hostseditor.HomeActivity;
import com.snail.hostseditor.core.NetEngine;
import com.snail.hostseditor.currentHost.CurrentHostAdapter;
import com.snail.hostseditor.currentHost.CurrentHostFragment;
import com.snail.hostseditor.currentHost.CurrentHostPresenter;
import com.snail.hostseditor.event.ShowCurrentHostEvent;
import com.snail.hostseditor.pannel.PanelFragment;
import com.snail.hostseditor.pannel.PanelPresenter;
import com.snail.hostseditor.task.ListHostsAsync;
import com.snail.hostseditor.task.ReplaceHostAsync;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                ListHostsAsync.class,
                ReplaceHostAsync.class,
                HomeActivity.class,
                NetEngine.class,
                PanelFragment.class,
                PanelPresenter.class,
                ShowCurrentHostEvent.class,
                CurrentHostFragment.class,
                CurrentHostPresenter.class,
                CurrentHostAdapter.class
        },
        includes = {
                NetModule.class,
        }
)
public class HostsEditorModule {

    @Provides
    @Singleton
    Bus provideBus() {
        return new Bus();
    }

}
