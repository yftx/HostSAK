package com.snail.hostseditor.event;

import com.snail.hostseditor.ui.extend.HostType;

import java.util.List;

/**
 * Created by yftx on 2/5/15.
 */
public class LoadHostTypeEvent {

    public List<HostType> hostTypes;

    public LoadHostTypeEvent(List<HostType> hostTypes) {
        this.hostTypes = hostTypes;
    }
}
