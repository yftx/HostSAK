package com.snail.hostseditor.task;

import com.snail.hostseditor.R;
import com.snail.hostseditor.core.Host;

import timber.log.Timber;

/**
 * AsyncTask that toggles one or many host entries and triggers a {@code TaskCompletedEvent} event.
 */
public class ToggleHostsAsync extends GenericTaskAsync {

    @Override
    protected void process(Host... params) {
        Timber.d("Toggle hosts");

        for (Host host : params) {
            host.toggleComment();
        }
    }

    @Override
    protected int getLoadingMsgRes() {
        return mFlagLoadingMsg ? R.string.loading_toggle_single : R.string.loading_toggle_multiple;
    }
}
