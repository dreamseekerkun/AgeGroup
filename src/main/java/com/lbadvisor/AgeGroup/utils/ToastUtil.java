package com.lbadvisor.AgeGroup.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.lbadvisor.AgeGroup.manager.MyApplication;


/**
 * 简单封装Toast ，防止全局修改Toast
 *
 * @author likun
 */
public class ToastUtil {
    public static void show(final String text) {
        final Context context = UIUtils.getContext();
        if (context == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public static void show(final int res) {
        final Context context = MyApplication.getApplication();
        if (context == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, UIUtils.getString(res), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
