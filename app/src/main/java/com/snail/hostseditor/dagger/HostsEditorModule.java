package com.snail.hostseditor.dagger;

import com.snail.hostseditor.currentHost.CurrentHostFragment;
import com.snail.hostseditor.currentHost.CurrentHostPresenter;
import com.snail.hostseditor.event.ShowCurrentHostEvent;
import com.snail.hostseditor.pannel.PanelFragment;
import com.snail.hostseditor.pannel.PanelPresenter;
import com.snail.hostseditor.HomeActivity;
import com.snail.hostseditor.task.AddEditHostAsync;
import com.snail.hostseditor.task.ListHostsAsync;
import com.snail.hostseditor.task.RemoveHostsAsync;
import com.snail.hostseditor.task.ReplaceHostAsync;
import com.snail.hostseditor.task.ToggleHostsAsync;
import com.snail.hostseditor.ui.addedit.AddEditHostActivity;
import com.snail.hostseditor.ui.addedit.AddEditHostFragment;
import com.snail.hostseditor.ui.extend.NetEngine;
import com.snail.hostseditor.ui.list.ListHostsActivity;
import com.snail.hostseditor.ui.list.ListHostsAdapter;
import com.snail.hostseditor.ui.list.ListHostsFragment;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                AddEditHostActivity.class,
                AddEditHostAsync.class,
                AddEditHostFragment.class,
                ListHostsActivity.class,
                ListHostsAsync.class,
                ListHostsFragment.class,
                RemoveHostsAsync.class,
                ToggleHostsAsync.class,
                ReplaceHostAsync.class,
                HomeActivity.class,
                NetEngine.class,
                PanelFragment.class,
                PanelPresenter.class,
                ShowCurrentHostEvent.class,
                CurrentHostFragment.class,
                CurrentHostPresenter.class,
                ListHostsAdapter.class
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
