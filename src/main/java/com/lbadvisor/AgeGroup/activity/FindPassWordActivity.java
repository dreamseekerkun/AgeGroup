package com.lbadvisor.AgeGroup.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import com.lbadvisor.AgeGroup.R;
import com.lbadvisor.AgeGroup.utils.ToastUtil;
import com.lbadvisor.AgeGroup.view.CaptchaTimeCount;
import com.lbadvisor.AgeGroup.view.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by likun on 15/10/20.
 */
public class FindPassWordActivity extends BaseActivity {

    @Bind(R.id.titlebar)
    TitleBar titlebar;
    @Bind(R.id.tv_findpwd_phone)
    TextView tvFindpwdPhone;
    @Bind(R.id.Verification_code)
    EditText VerificationCode;
    @Bind(R.id.tv_findpwd_get_Verification_code)
    TextView tvFindpwdGetVerificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        CaptchaTimeCount time = new CaptchaTimeCount(tvFindpwdGetVerificationCode, 60000, 1000);
        time.start();
        tvFindpwdPhone.setText("18690184545");
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

    @OnClick(R.id.tv_findpwd_get_Verification_code)
    void getVerCode() {
        CaptchaTimeCount time = new CaptchaTimeCount(tvFindpwdGetVerificationCode, 60000, 1000);
        time.start();
    }

    @OnClick(R.id.btn_findpwd_next)
    void findPwdNext() {
        String Code = VerificationCode.getText().toString().trim();
        if (TextUtils.isEmpty(Code)) {
            ToastUtil.show("请输入验证码");
        } else {
            startActivity(SetLoginPwdActivity.class, false);
        }
    }
}
