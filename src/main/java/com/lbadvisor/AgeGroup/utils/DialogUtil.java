package com.lbadvisor.AgeGroup.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import com.lbadvisor.AgeGroup.R;


/**
 * @Description: 提示框
 * @author likun
 * 
 */
public class DialogUtil {
	private static Context context = null;
	private static Dialog dialog;
	private static boolean isShowing = false;

	public static void show(Context context) {
		DialogUtil.context = context;
		// 提示框已经显示
		if (isShowing) {
			return;
		}
		if(context!=null){
			
			dialog = new Dialog(context, R.style.base_dialog);
			dialog.setCancelable(false);
			
			View view = View.inflate(DialogUtil.context, R.layout.progressbar_loading, null);
			ImageView iv = (ImageView) view.findViewById(R.id.loading_dialog_iv);
			Animation animation = new RotateAnimation(0f, 353f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			animation.setRepeatCount(Animation.INFINITE);
			animation.setDuration(1500);
			Interpolator i = new LinearInterpolator();
			animation.setInterpolator(i);
			animation.setRepeatMode(0);
			iv.setAnimation(animation);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			dialog.addContentView(view, params);
			
			try {
				if (dialog != null) {
					if (!dialog.isShowing()) {
						dialog.show();
						isShowing = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void close() {

		if (dialog != null) {
			if (dialog.isShowing()) {
				try {
					dialog.dismiss();
					isShowing = false;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
