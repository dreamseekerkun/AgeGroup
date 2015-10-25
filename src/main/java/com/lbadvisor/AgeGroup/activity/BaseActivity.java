package com.lbadvisor.AgeGroup.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Window;
import com.lbadvisor.AgeGroup.R;
import com.lbadvisor.AgeGroup.manager.AppManager;
import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity {

	public Context context;
	public static BaseActivity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		AppManager.getInstance().addActivity(this);
//		ButterKnife.bind(this);
		activity = this;
		context = this;
	}

	@Override
	public void onBackPressed() {
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	protected void startActivity(Class cls){
		startActivity(cls, null, true);
	}
	protected void startActivity(Class cls,boolean isfinish){
		startActivity(cls,null,isfinish);
	}
	protected void startActivity(Class cls,Bundle bundle){
		startActivity(cls, bundle, true);
	}
	protected void startActivity(Class cls,Bundle bundle,boolean isfinish){
		if(cls ==null || activity ==null ) return;
		Intent intent = new Intent();
		if(bundle!=null)
			intent.putExtras(bundle);
		intent.setClass(activity, cls);
		startActivity(intent);
		overridePendingTransition(R.anim.activity_out, R.anim.activity_in);
		if(isfinish) finish();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		AppManager.getInstance().finishActivity(this);
		ButterKnife.unbind(this);
	}

	public void onPause() {
		super.onPause();
	}

	/**
	 * 设置布局文件
	 */
//	public abstract void setView();

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			overridePendingTransition(R.anim.activity_finish_in, R.anim.activity_finish_out);
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

}