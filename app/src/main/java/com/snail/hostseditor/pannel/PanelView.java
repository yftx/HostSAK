package com.snail.hostseditor.pannel;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.snail.hostseditor.model.HostType;

import java.util.List;

/**
 * Created by yftx on 4/21/15.
 */
public interface PanelView extends MvpLceView<List<HostType>> {
    void showAlertReplaceHostDialog(HostType hostType);
    void showReplacingHostDialog();
    void hideReplacingHostDialog();
}
