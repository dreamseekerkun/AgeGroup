package com.lbadvisor.AgeGroup.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.lbadvisor.AgeGroup.R;
import com.lbadvisor.AgeGroup.adapter.MyFragmentPageAdapter;
import com.lbadvisor.AgeGroup.utils.UIUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 文字切换标签+ViewPager内容区控件
 *
 * @author likun
 */
public class ViewPagerIndicatorView extends LinearLayout implements TabIndicatorView.OnIndicateChangeListener, OnPageChangeListener {
    private TabIndicatorView tabIndicatorView;
    private ViewPager viewPager;

    public ViewPagerIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView();
    }

    public ViewPagerIndicatorView(Context context) {
        super(context);
        this.initView();
    }

    private void initView() {
        this.setOrientation(LinearLayout.VERTICAL);

        this.tabIndicatorView = new TabIndicatorView(getContext());
        this.viewPager = new ViewPager(UIUtils.getContext());
        viewPager.setId(R.id.vp);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        this.addView(tabIndicatorView, params);
        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.addView(viewPager, params);
        this.tabIndicatorView.setOnIndicateChangeListener(this);
        this.viewPager.setOnPageChangeListener(this);

    }

    /**
     * 设置显示标签文字及对应内容布局
     *
     * @param titleViewMap 标题及对应View map数据
     */
    public void setupLayout(Map<List, List> titleViewMap, FragmentManager fragmentManager) {
        if (titleViewMap == null || titleViewMap.size() == 0) {
            throw new NullPointerException();
        }

        List<String> textList = new ArrayList<>();
        List<Fragment> viewList = new ArrayList<>();

        final Iterator<Entry<List, List>> iterator = titleViewMap.entrySet().iterator();
        while (iterator.hasNext()) {// 生成数据列表
            final Entry<List, List> item = iterator.next();
            textList = item.getKey();
            viewList = item.getValue();
        }

        // 初始化TextTabIndicateView及ViewPager
        this.tabIndicatorView.setupLayout(textList);
        this.viewPager.setAdapter(new MyFragmentPageAdapter(fragmentManager, viewList));
    }

    @Override
    public void onTabChanged(int position) {
        viewPager.setCurrentItem(position, true);
    }

    @Override
    public void onPageSelected(int position) {
        this.tabIndicatorView.setCurrentTab(position, false);//设置不通知接口返回位置
    }


    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

}
