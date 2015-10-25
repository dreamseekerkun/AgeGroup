package com.lbadvisor.AgeGroup.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import com.lbadvisor.AgeGroup.R;
import com.lbadvisor.AgeGroup.fragment.DiscoveryFragment;
import com.lbadvisor.AgeGroup.fragment.GroupFragment;
import com.lbadvisor.AgeGroup.view.ViewPagerIndicatorView;
import com.nineoldandroids.view.ViewHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.viewpager_indicator_view)
    ViewPagerIndicatorView viewpagerIndicatorView;
    @Bind(R.id.drawerlayout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
//        initEvents();
    }


    @OnClick(R.id.iv_userhead)
    void usersetting() {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    private void initView() {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.RIGHT);
        List<String> textList = new ArrayList<String>();
        List<Fragment> viewList = new ArrayList<Fragment>();
        textList.add("参团");
        textList.add("发现");
        viewList.add(GroupFragment.newInstance());
        viewList.add(DiscoveryFragment.newInstance());
        Map<List, List> map = new HashMap<>();
        map.put(textList, viewList);
        viewpagerIndicatorView.setupLayout(map,getSupportFragmentManager());


    }

    /**
     * 仿QQ滑动动画
     */
    private void initEvents() {
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;
                if (drawerView.getTag().equals("LEFT")) {
//
                    float leftScale = 1 - 0.3f * scale;

                    ViewHelper.setScaleX(mMenu, leftScale);
                    ViewHelper.setScaleY(mMenu, leftScale);
                    ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
                    ViewHelper.setTranslationX(mContent,
                            mMenu.getMeasuredWidth() * (1 - scale));
                    ViewHelper.setPivotX(mContent, 0);
                    ViewHelper.setPivotY(mContent,
                            mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }
}
