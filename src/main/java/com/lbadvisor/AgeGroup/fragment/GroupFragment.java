package com.lbadvisor.AgeGroup.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lbadvisor.AgeGroup.R;
import com.lbadvisor.AgeGroup.activity.GroupConfirmActivity;
import com.lbadvisor.AgeGroup.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by likun on 15/10/21.
 */
public class GroupFragment extends BaseFragment {

    @Bind(R.id.et_input_vericode)
    EditText etInputVericode;

    public static GroupFragment newInstance() {
        GroupFragment groupFragment = new GroupFragment();
        return groupFragment;
    }

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_group, null);
        return view;
    }

    @OnClick(R.id.tv_submit)
    void submit() {
        Intent intent = new Intent(getActivity(), GroupConfirmActivity.class);
        UIUtils.startActivity(intent);
    }
}
