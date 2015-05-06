package com.snail.hostseditor.event;

import com.snail.hostseditor.model.Host;

public class StartAddEditActivityEvent {

    /**
     * The Host entry to modify (edit mode), or {@code null} (add mode).
     */
    public final Host host;

    public StartAddEditActivityEvent(Host addEditHost) {
        host = addEditHost;
    }
}
