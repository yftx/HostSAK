package com.snail.hostseditor.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.snail.hostseditor.R;
import com.snail.hostseditor.model.Host;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Custom component that implements the Checkable interface.
 *
 * @see "http://www.marvinlabs.com/2010/10/29/custom-listview-ability-check-items/"
 */
public class CheckableRelativeLayout extends RelativeLayout implements Checkable {

    @InjectView(R.id.hostItemIp)
    TextView mIp;
    @InjectView(R.id.hostItemHostname)
    TextView mHostname;
    @InjectView(R.id.hostItemComment)
    TextView mComment;
    @InjectView(R.id.hostItemCheckbox)
    InertCheckBox mCheckbox;


    public CheckableRelativeLayout(Context context) {
        super(context);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);
    }

    public void drawItem(Host host) {
        String ip = String.format(Locale.US, "%s%s", (host.isCommented() ? Host.STR_COMMENT : ""), host.getIp());

        mIp.setText(ip);
        mHostname.setText(host.getHostName());
        mCheckbox.setChecked(false);

        String comment = host.getComment();
        if (TextUtils.isEmpty(comment)) {
            mComment.setVisibility(View.GONE);
        } else {
            mComment.setText(String.format(Locale.US, "%s%s", Host.STR_COMMENT, comment));
            mComment.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean isChecked() {
        return mCheckbox.isChecked();
    }

    @Override
    public void setChecked(boolean checked) {
        mCheckbox.setChecked(checked);
    }

    @Override
    public void toggle() {
        mCheckbox.toggle();
    }
}
