package com.lbadvisor.AgeGroup.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by likun on 2015/10/19.
 */
public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();
        View view = setView(inflater, container);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化数据
     */
    protected void initData() {}

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        mActivity = null;
    }

    /**
     * 初始化布局
     * @param inflater
     * @param container
     * @return
     */
    protected abstract View setView(LayoutInflater inflater, ViewGroup container);
}
