package com.lbadvisor.AgeGroup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

//继承BaseAdapter 可减少冗余代码
public abstract class MyBaseAdapter<T> extends BaseAdapter {
	public List<T> list;
	public LayoutInflater inflater;
	public Context context;


	public MyBaseAdapter(List<T> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		inflater= LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list!=null&&!list.isEmpty()?list.size():0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return creatView(position, convertView, parent);
	}
	public abstract View creatView(int position, View convertView, ViewGroup parent);

}
