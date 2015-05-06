package com.snail.hostseditor.event;

import com.snail.hostseditor.model.Host;

import java.util.List;

/**
 * Created by yftx on 2/5/15.
 */
public class LoadHostsEvent {
    private List<Host> hosts;

    public LoadHostsEvent(List<Host> hosts) {
        this.hosts = hosts;
    }

    public Host[] getHosts(){
        return hosts.toArray(new Host[hosts.size()]);
    }

}
