package com.lbadvisor.AgeGroup.fragment;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lbadvisor.AgeGroup.R;
import com.lbadvisor.AgeGroup.adapter.DiscoveryListAdapter;
import com.lbadvisor.AgeGroup.adapter.MyPagerAdapter;
import com.lbadvisor.AgeGroup.bean.GroupListInfo;
import com.lbadvisor.AgeGroup.utils.DateUtils;
import com.lbadvisor.AgeGroup.utils.SPUtils;
import com.lbadvisor.AgeGroup.utils.UIUtils;
import com.lbadvisor.AgeGroup.view.XListView;

import java.util.ArrayList;


/**
 * Created by likun on 15/10/19.
 */
public class DiscoveryFragment extends BaseFragment implements XListView.IXListViewListener {

    private View headView;
    private ViewPager viewPager;
    private LinearLayout llBannerPoint;
    private ArrayList<String> images = new ArrayList<>();//存储轮播图集合
    private ImageView[] bannerPoints;
    private MyPagerAdapter myPagerAdapter;
    private Handler handler;
    private int pageNum;
    ArrayList<GroupListInfo> list = new ArrayList<>();

    //    @Bind(R.id.xlisteview_discovery)
    private XListView xlisteviewDiscovery;

    public static DiscoveryFragment newInstance() {
        DiscoveryFragment discoveryFragment = new DiscoveryFragment();
        return discoveryFragment;
    }


    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_discovery, null);
        xlisteviewDiscovery = (XListView) view.findViewById(R.id.xlisteview_discovery);
        DiscoveryListAdapter disadapter = new DiscoveryListAdapter(list, UIUtils.getContext());
        xlisteviewDiscovery.setAdapter(disadapter);
        xlisteviewDiscovery.setXListViewListener(this);
        headView = UIUtils.inflate(R.layout.headview_mainfragment);
        viewPager = (ViewPager) headView.findViewById(R.id.mainfragment_viewPager);
        llBannerPoint = (LinearLayout) headView.findViewById(R.id.ll_banner_point);
        handler = new Handler(Looper.getMainLooper());

        initHeadView();
        return view;
    }

    private void initHeadView() {
        images.add("http://img5.imgtn.bdimg.com/it/u=2645226784,1850525231&fm=21&gp=0.jpg");
        images.add("http://img0.imgtn.bdimg.com/it/u=2813188735,1537699059&fm=21&gp=0.jpg");
        images.add("http://img3.imgtn.bdimg.com/it/u=2484202869,2302280260&fm=21&gp=0.jpg");
        images.add("http://img0.imgtn.bdimg.com/it/u=4168762024,1922499492&fm=21&gp=0.jpg");
        images.add("http://img0.imgtn.bdimg.com/it/u=2247491008,298062800&fm=21&gp=0.jpg");
        addHeader(headView);
    }


    @Override
    protected void initData() {

        list.add(new GroupListInfo(3, "1000", "3000", "500", "2015-10-31 11:10:00"));
        list.add(new GroupListInfo(3, "1000", "3000", "500", "2015-10-31 11:10:00"));
        list.add(new GroupListInfo(3, "1000", "3000", "500", "2015-10-31 11:10:00"));
        list.add(new GroupListInfo(3, "1000", "3000", "500", "2015-10-31 11:10:00"));
        if(list.size()>3){//控制显示多少个条目，超过3个时，显示加载更多
            xlisteviewDiscovery.setPullLoadEnable(true);
        }else{
            xlisteviewDiscovery.setPullLoadEnable(false);
        }
        onLoad();
        SPUtils.put(getActivity(), "loadtime", "discoveryloadtime", System.currentTimeMillis() + "");
    }

    /**
     * add the listview header
     */
    private void addHeader(View headView) {

        bannerPoints = new ImageView[images.size()];
        for (int i = 0; i < images.size(); i++) {
            bannerPoints[i] = new ImageView(getActivity());
            bannerPoints[i].setPadding(20, 0, 0, 5);
            bannerPoints[i].setImageResource(R.drawable.icon_banner_point_off);
            llBannerPoint.addView(bannerPoints[i]);
        }
        if (images.size() > 0)
            bannerPoints[pageNum % images.size()].setImageResource(R.drawable.icon_banner_point_on);
        myPagerAdapter = new MyPagerAdapter(UIUtils.getContext(), images);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        autoScroll();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (bannerPoints != null) {
                    for (int i = 0; i < images.size(); i++) {
                        bannerPoints[i].setImageResource(R.drawable.icon_banner_point_off);
                    }
                    if (images.size() > 0)
                        bannerPoints[position % images.size()].setImageResource(R.drawable.icon_banner_point_on);
                    try {
                        bannerPoints[position % images.size()].setImageResource(R.drawable.icon_banner_point_on);
                    } catch (Exception e) {
                    }
                    pageNum = position;
                }
                if (position < 1) {
                    position = images.size();
                    viewPager.setCurrentItem(position);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        xlisteviewDiscovery.addHeaderView(headView);
    }

    /**
     * 自动滚动
     */
    private void autoScroll() {
        UIUtils.post2MainThreadDelayed(new Runnable() {

            @Override
            public void run() {
                int page = viewPager.getCurrentItem();
                page = page + 1;
                viewPager.setCurrentItem(page);
                autoScroll();
            }
        }, 5000);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        initData();
    }

    private void onLoad() {
        String time = (String)SPUtils.get(getActivity(), "loadtime","discoveryloadtime","");
        if(!"".equals(time)) time = DateUtils.ConvertTime(Long.parseLong(time));
        if ("".equals(time)) {
            xlisteviewDiscovery.setRefreshTime("还未刷新过");
        } else {
            xlisteviewDiscovery.setRefreshTime(time);
        }
        xlisteviewDiscovery.stopRefresh();
        xlisteviewDiscovery.stopLoadMore();
    }
}
