package com.lbadvisor.AgeGroup.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.lbadvisor.AgeGroup.R;
import com.lbadvisor.AgeGroup.utils.ImageLoad;

import java.util.List;


public class MyPagerAdapter extends PagerAdapter {
	private List<String> list;
	private Context context;
	private int mChildCount = 0;

	@Override
	public void notifyDataSetChanged() {
		mChildCount = getCount();
		super.notifyDataSetChanged();
	}

	@Override
	public int getItemPosition(Object object) {
		if (mChildCount > 0) {
			mChildCount--;
			return POSITION_NONE;
		}
		return super.getItemPosition(object);
	}

	public MyPagerAdapter(Context context, List<String> list) {
		this.context =context;
		this.list = list;
	}

//	public void setActionData(ArrayList<ActionData> aclist) {
//		this.acdatelist = aclist;
//	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	// 添加item
	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		if (list.size() > 0) {
			View view = View.inflate(context, R.layout.fragment_main_headview, null);
			ImageView hvIm = (ImageView) view.findViewById(R.id.fragment_main_headview_iv);
			ProgressBar pb = (ProgressBar) view.findViewById(R.id.fragment_main_headview_pb);
			ImageLoad.getInstance().display(hvIm, pb, list.get(position % list.size()));
			container.addView(view);
			return view;
		} else {
			return null;
		}
	}

	// 删除item
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

}
