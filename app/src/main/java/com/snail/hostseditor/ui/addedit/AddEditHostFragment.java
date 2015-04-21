package com.snail.hostseditor.ui.addedit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.snail.hostseditor.R;
import com.snail.hostseditor.core.Host;
import com.snail.hostseditor.core.util.InetAddresses;
import com.snail.hostseditor.event.CreatedHostEvent;
import com.snail.hostseditor.ui.BaseFragment;

import java.util.regex.Pattern;

import butterknife.InjectView;
import butterknife.OnClick;

public class AddEditHostFragment extends BaseFragment {

    public static final String TAG = AddEditHostFragment.class.getSimpleName();

    private static final String ARG_HOST = "mInitialHost";
    private static final Pattern HOSTNAME_INVALID_CHARS_PATTERN = Pattern.compile("^.*[#'\",\\\\]+.*$");

    @Arg
    Host mInitialHost; // "edit mode" only - null for "add mode"
    private AlertDialog mErrorAlert;

    @InjectView(R.id.addEditHostIp)
    EditText mIp;
    @InjectView(R.id.addEditHostName)
    EditText mHostName;
    @InjectView(R.id.addEditComment)
    EditText mComment;
    @InjectView(R.id.addEditCommentLabel)
    TextView mCommentLabel;
    @InjectView(R.id.addEditHostButton)
    Button mButton;

    public static AddEditHostFragment newInstance(Host hostToEdit) {
        AddEditHostFragment fragment = new AddEditHostFragment();

        if (hostToEdit != null) {
            Bundle args = new Bundle();
            args.putParcelable(ARG_HOST, hostToEdit);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mInitialHost = arguments.getParcelable(ARG_HOST);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.add_edit_host_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mInitialHost == null) {
            mButton.setText(R.string.add_host_title);
        } else {
            mIp.setText(mInitialHost.getIp());
            mHostName.setText(mInitialHost.getHostName());
            mButton.setText(R.string.edit_host_title);

            String comment = mInitialHost.getComment();
            if (!TextUtils.isEmpty(comment)) {
                mComment.setText(comment);
                toggleCommentVisibility();
            }
        }
    }

    @Override
    public void onStop() {
        if (mErrorAlert != null) {
            mErrorAlert.dismiss();
        }
        super.onStop();
    }

    @OnClick(R.id.addEditHostButton)
    void onAddEditHostButtonClicked(Button button) {
        if (button.getId() == R.id.addEditHostButton) {
            String ip = mIp.getText().toString();
            String hostname = mHostName.getText().toString();
            String comment = mComment.getText().toString();
            if (TextUtils.isEmpty(comment)) {
                comment = null;
            }
            int error = checkFormErrors(ip, hostname);

            if (error == 0) {
                Host edited = new Host(ip, hostname, comment, false, true);
                mBus.post(new CreatedHostEvent(mInitialHost, edited));
            } else {
                mErrorAlert = new AlertDialog.Builder(mActivity)
                        .setTitle(R.string.add_edit_error_title)
                        .setMessage(error)
                        .setCancelable(true)
                        .setNeutralButton(R.string.add_edit_error_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing
                            }
                        })
                        .create();
                mErrorAlert.show();
            }
        }
    }

    public boolean hasComment() {
        return mComment.getVisibility() == View.VISIBLE;
    }

    public void toggleCommentVisibility() {
        int visibility;
        if (hasComment()) {
            visibility = View.GONE;
            mComment.setText("");
        } else {
            visibility = View.VISIBLE;
        }

        mComment.setVisibility(visibility);
        mCommentLabel.setVisibility(visibility);
    }

    private int checkFormErrors(String ip, String hostname) {
        int error = 0;

        if (TextUtils.isEmpty(hostname) || HOSTNAME_INVALID_CHARS_PATTERN.matcher(hostname).matches()) {
            error = R.string.add_edit_error_hostname;
        }
        if (TextUtils.isEmpty(ip) || !InetAddresses.isInetAddress(ip)) {
            error = R.string.add_edit_error_ip;
        }
        return error;
    }
}
