package com.snail.hostseditor.core.task;

import android.os.AsyncTask;

import com.snail.hostseditor.App;
import com.snail.hostseditor.model.Host;
import com.snail.hostseditor.core.HostsManager;
import com.snail.hostseditor.event.LoadingEvent;
import com.snail.hostseditor.event.TaskCompletedEvent;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import timber.log.Timber;

public abstract class GenericTaskAsync extends AsyncTask<Host, Void, Void> {

    @Inject
    Bus mBus;
    @Inject
    HostsManager mHostsManager;
    @Inject
    App mApp;

    protected boolean mFlagLoadingMsg; // which loading message (between 2) to display: (singular/plural) - (add/edit).

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mBus.post(new LoadingEvent(true, getLoadingMsgRes()));
    }

    @Override
    protected Void doInBackground(Host... params) {
        process(params);
        if (!mHostsManager.saveHosts(mApp)) {
            cancel(false);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        Timber.d("Task fully executed");
        mBus.post(new TaskCompletedEvent(getClass().getSimpleName(), true));
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Timber.w("Task cancelled");
        mBus.post(new TaskCompletedEvent(getClass().getSimpleName(), false));
    }

    public void init(boolean flagMsg) {
        mFlagLoadingMsg = flagMsg;
    }

    /**
     * This method should edit the main {@code List<Host>}.
     *
     * @param params selected Hosts from the main ListView.
     */
    protected abstract void process(Host... params);

    protected abstract int getLoadingMsgRes();
}
