package com.lbadvisor.AgeGroup.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.lbadvisor.AgeGroup.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by likun on 15/10/23.
 */
public class GroupConfirmActivity extends BaseActivity {

    @Bind(R.id.iv_userhead)
    ImageView ivUserhead;
    @Bind(R.id.tv_group_name)
    TextView tvGroupName;
    @Bind(R.id.tv_group_invitecode)
    TextView tvGroupInvitecode;
    @Bind(R.id.tv_confirm)
    TextView tvConfirm;
    @Bind(R.id.tv_cancel)
    TextView tvCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_confirm);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        tvGroupName.setText("齐天大圣团");
        tvGroupInvitecode.setText("孙悟空 ABD232H");
    }
}
