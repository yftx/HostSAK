package com.snail.hostseditor.dagger;

import com.snail.hostseditor.pannel.PannelActivity;
import com.snail.hostseditor.pannel.PannelFragment;
import com.snail.hostseditor.pannel.PannelPresenter;
import com.snail.hostseditor.task.AddEditHostAsync;
import com.snail.hostseditor.task.ListHostsAsync;
import com.snail.hostseditor.task.RemoveHostsAsync;
import com.snail.hostseditor.task.ReplaceHostAsync;
import com.snail.hostseditor.task.ToggleHostsAsync;
import com.snail.hostseditor.ui.addedit.AddEditHostActivity;
import com.snail.hostseditor.ui.addedit.AddEditHostFragment;
import com.snail.hostseditor.ui.extend.NetEngine;
import com.snail.hostseditor.ui.list.ListHostsActivity;
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
                PannelActivity.class,
                NetEngine.class,
                PannelPresenter.class,
                PannelFragment.class,
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
