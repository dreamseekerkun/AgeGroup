package com.lbadvisor.AgeGroup.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.lbadvisor.AgeGroup.R;
import com.lbadvisor.AgeGroup.utils.ToastUtil;
import com.lbadvisor.AgeGroup.utils.UIUtils;
import com.lbadvisor.AgeGroup.view.CaptchaTimeCount;
import com.lbadvisor.AgeGroup.view.ClearableEditText;
import com.lbadvisor.AgeGroup.view.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by likun on 15/9/27.
 */
public class RegisterActivityFirst extends BaseActivity {

    @Bind(R.id.titlebar)
    TitleBar titlebar;
    @Bind(R.id.tv_get_Verification_code)
    TextView tvGetVerificationCode;
    @Bind(R.id.phone_num)
    ClearableEditText phoneNum;
    @Bind(R.id.invite_code)
    ClearableEditText inviteCode;
    String phone;
    @Bind(R.id.Verification_code)
    EditText VerificationCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_first);
        ButterKnife.bind(this);
        titlebar.setOnLeftAndRightClickListener(new TitleBar.OnLeftAndRightClickListener() {
            @Override
            public void onLeftButtonClick() {
                finish();
            }

            @Override
            public void onRightButtonClick() {
            }
        });
    }

    @OnClick(R.id.tv_get_Verification_code)
    void getVerificCode() {
        phoneNum.setClickable(false);
        phone = phoneNum.getText().toString().trim();
        if (UIUtils.checkPhone(phone)) {
            phoneNum.setClickable(true);
            CaptchaTimeCount time = new CaptchaTimeCount(tvGetVerificationCode, 60000, 1000);
            time.start();
        }
    }

    @OnClick(R.id.register_next)
    void registerNext() {
        String Code = VerificationCode.getText().toString().trim();
        if (TextUtils.isEmpty(Code)) {
            ToastUtil.show("请输入验证码");
        } else {
            startActivity(RegisterActivityDone.class, false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
