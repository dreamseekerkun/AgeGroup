package com.lbadvisor.AgeGroup.view;

import android.os.CountDownTimer;
import android.widget.TextView;
import com.lbadvisor.AgeGroup.utils.ToastUtil;
import com.lbadvisor.AgeGroup.utils.UIUtils;


/**
 * 验证码倒计时类
 */
public class CaptchaTimeCount extends CountDownTimer {
	
	private TextView tvCountDownTimer;
	
	public CaptchaTimeCount(TextView tvCountDownTimer, long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
		this.tvCountDownTimer = tvCountDownTimer;
		ToastUtil.show("验证码已发送，请注意查收！");
	}

	@Override
	public void onFinish() {// 计时完毕时触发
		tvCountDownTimer.setText("重获验证码");
		UIUtils.updateTextViewState(tvCountDownTimer, true);
	}

	@Override
	public void onTick(long millisUntilFinished) {// 计时过程显示
		tvCountDownTimer.setText(millisUntilFinished / 1000 + "秒后重发");
		UIUtils.updateTextViewState(tvCountDownTimer, false);
	}
}
