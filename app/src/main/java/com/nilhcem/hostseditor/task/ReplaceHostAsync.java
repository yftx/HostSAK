package com.nilhcem.hostseditor.task;

import com.nilhcem.hostseditor.R;
import com.nilhcem.hostseditor.core.Host;

import java.util.Collections;
import java.util.List;

/**
 * Created by yftx on 2/3/15.
 */
public class ReplaceHostAsync extends GenericTaskAsync {

    @Override
    protected void process(Host... params) {

        List<Host> hosts = mHostsManager.getHosts(false);
        hosts.clear();
        Collections.addAll(hosts, params);
    }

    @Override
    protected int getLoadingMsgRes() {
        return mFlagLoadingMsg ? R.string.loading_add : R.string.loading_edit;
    }
}