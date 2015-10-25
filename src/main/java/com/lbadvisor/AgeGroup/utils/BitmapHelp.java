package com.lbadvisor.AgeGroup.utils;

import com.lbadvisor.AgeGroup.manager.MyApplication;
import com.lidroid.xutils.BitmapUtils;


public class BitmapHelp {

	private BitmapHelp() {
	}

	private static BitmapUtils bitmapUtils;

	public static BitmapUtils getBitmapUtils() {
		if (bitmapUtils == null) {
			bitmapUtils = new BitmapUtils(MyApplication.getApplication());
		}
		return bitmapUtils;
	}
}
