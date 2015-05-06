package com.snail.hostseditor.event;

import com.snail.hostseditor.model.Host;

import java.util.List;

/**
 * Sent to the bus when hosts need to be refreshed.
 */
public class RefreshHostsEvent {

    public final List<Host> hosts;

    public RefreshHostsEvent(List<Host> refreshedHosts) {
        hosts = refreshedHosts;
    }
}
