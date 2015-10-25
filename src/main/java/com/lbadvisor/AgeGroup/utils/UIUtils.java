package com.lbadvisor.AgeGroup.utils;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.lbadvisor.AgeGroup.R;
import com.lbadvisor.AgeGroup.activity.BaseActivity;
import com.lbadvisor.AgeGroup.manager.MyApplication;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;



public class UIUtils {
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * px2dip
     */

    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static String[] getStringArray(int id) {
        return getResource().getStringArray(id);
    }

    public static Resources getResource() {
        return getContext().getResources();
    }

    public static MyApplication getContext() {
        return MyApplication.getApplication();
    }


    public static void showToast(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(int str_id) {
        Toast.makeText(getContext(), str_id, Toast.LENGTH_SHORT).show();
    }

    /**
     * 填充布局
     *
     * @param layout_id
     * @return
     */
    public static View inflate(int layout_id) {
        return View.inflate(getContext(), layout_id, null);
    }

    public static Drawable getDrawable(int id) {
        return getResource().getDrawable(id);
    }

    public static String getString(int id) {
        return getResource().getString(id);
    }

    public static int getColor(int id) {
        return getResource().getColor(id);
    }

    public static void startActivity(Intent intent) {
        if (BaseActivity.activity != null) {
            BaseActivity.activity.startActivity(intent);
            BaseActivity.activity.overridePendingTransition(R.anim.activity_out, R.anim.activity_in);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        }
    }

    public static void post2MainThread(Runnable r) {
        MyApplication.getHandler().post(r);
    }

    public static void post2MainThreadDelayed(Runnable r, long delayMillis) {
        MyApplication.getHandler().postDelayed(r, delayMillis);
    }

    public static String addRequestUrlParams(String path, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(path);
        int i = 0;

        char symbol = '?';
        if (path.contains("?")) {
            symbol = '&';
        }

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (i == 0) {
                sb.append(symbol);
            } else {
                sb.append("&");
            }
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            ++i;
        }
        return sb.toString();

    }

    public static Map<String, String> addBasicParams() {
        Map<String, String> params = new HashMap<String, String>();
//		params.put("deviceId", Utility.generateUDID());
//		params.put("channelId", TTC.getInstance().getChannel());
//		params.put("v", Utility.getLocalVersion(TTC.getInstance().getContext()));
        return params;
    }

    public static int getResId(String resName, Class<?> c) {
        LogUtils.e("getResId", resName);
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void updateTextViewState(View textview, boolean clickable) {
        if (clickable) {
            if (!textview.isClickable()) {
                textview.setClickable(true);
//				button.setBackgroundResource(R.drawable.button_bule);
            }

        } else {
            if (textview.isClickable()) {
                textview.setClickable(false);
//				button.setBackgroundResource(R.drawable.button_gray);
            }
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
        String telRegex = "[1][0-9]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        return mobiles.matches(telRegex);
    }

    public static boolean checkPhone(String phone){
        boolean ret = true;
        if(TextUtils.isEmpty(phone)){
            UIUtils.showToast("请先输入手机号码");
            ret = false;
        }else if(!isMobileNO(phone)){
            UIUtils.showToast("手机号码输入格式不对,请重新输入");
            ret = false;
        }
        return ret;
    }

    /**
     * 转换图片成圆形
     *
     * @param bitmap 传入Bitmap对象
     * @return
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(output);
        final int color = 0xFF424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right,
                (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top,
                (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        paint.setColor(0xFFc9c9c9);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawCircle(rectF.left + (rectF.right - rectF.left) / 2,
                rectF.top + (rectF.bottom - rectF.top) / 2,
                (rectF.right - rectF.left) / 2, paint);
        return output;
    }
}
