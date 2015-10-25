package com.lbadvisor.AgeGroup.activity;

import android.os.Bundle;
import com.lbadvisor.AgeGroup.R;
import com.lbadvisor.AgeGroup.view.TitleBar;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by likun on 15/9/27.
 */
public class RegisterActivityDone extends BaseActivity {

    @Bind(R.id.titlebar)
    TitleBar titlebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_done);
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
