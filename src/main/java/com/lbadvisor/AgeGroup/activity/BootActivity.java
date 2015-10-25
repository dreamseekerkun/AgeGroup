package com.lbadvisor.AgeGroup.activity;

import android.os.Bundle;
import android.text.TextUtils;

import com.lbadvisor.AgeGroup.R;
import com.lbadvisor.AgeGroup.utils.UIUtils;
import com.lbadvisor.AgeGroup.view.CircularImageView;
import com.lbadvisor.AgeGroup.view.ClearableEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by likun on 15/9/27.
 */
public class BootActivity extends BaseActivity {

    @Bind(R.id.et_register_phone)
    ClearableEditText etRegisterPhone;
    @Bind(R.id.et_register_pwd)
    ClearableEditText etRegisterpwd;
    @Bind(R.id.iv_avator)
    CircularImageView ivAvator;
    private String phone;
    private String pwd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
        ButterKnife.bind(this);
        etRegisterPhone.setText("18690184545");
        etRegisterpwd.setText("123456");

    }

    @OnClick(R.id.btn_login)
    void login() {
        phone = etRegisterPhone.getText().toString().trim();
        pwd = etRegisterpwd.getText().toString().trim();
        if (checkInput()) {
            startActivity(MainActivity.class, true);
        }
    }

    @OnClick(R.id.tv_register)
    void register() {
        startActivity(RegisterActivityFirst.class, false);
    }

    @OnClick(R.id.tv_forget_pwd)
    void forgetPwd() {
        phone = etRegisterPhone.getText().toString().trim();
        if (UIUtils.checkPhone(phone)) startActivity(FindPassWordActivity.class, false);
    }


    private boolean checkInput() {
        boolean ret = true;
        if (!UIUtils.checkPhone(phone)) {
            return false;
        }
        if (TextUtils.isEmpty(pwd)) {
            UIUtils.showToast("请输入密码");
            ret = false;
        }
        return ret;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
