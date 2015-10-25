package com.lbadvisor.AgeGroup.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;

import com.lbadvisor.AgeGroup.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;



/**
 * 图片加载
 *
 * @author likun
 */
public class ImageLoad {
    private static BitmapUtils bitmapUtils;
    private static ImageLoad instance = null;

    public static ImageLoad getInstance() {
        if (instance == null) {
            synchronized (ImageLoad.class) {
                if (instance == null) {
                    instance = new ImageLoad();
                }
            }
        }
        return instance;
    }

    private ImageLoad() {
    }

    static {
        bitmapUtils = new BitmapUtils(UIUtils.getContext());
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_launcher);// 加载失败图片
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);// 设置图片压缩类型
        bitmapUtils.configDefaultLoadingImage(R.drawable.ic_launcher);// 默认背景图片
    }

    /**
     * 图片回调
     */
    public class CustomBitmapLoadCallBack extends DefaultBitmapLoadCallBack<ImageView> {

        @Override
        public void onLoading(ImageView container, String uri, BitmapDisplayConfig config, long total, long current) {
            ((ProgressBar) container.getTag()).setVisibility(View.VISIBLE);
        }

        @Override
        public void onLoadCompleted(ImageView container, String uri, Bitmap bitmap, BitmapDisplayConfig config, BitmapLoadFrom from) {
            ((ProgressBar) container.getTag()).setVisibility(View.GONE);
            fadeInDisplay(container, bitmap, true);
        }

        @Override
        public void onLoadFailed(ImageView container, String uri, Drawable drawable) {
            ((ProgressBar) container.getTag()).setVisibility(View.GONE);
            container.setScaleType(ScaleType.FIT_XY);
            container.setImageResource(R.drawable.ic_launcher);
        }
    }

    // private static final ColorDrawable TRANSPARENT_DRAWABLE = new
    // ColorDrawable(android.R.color.transparent);

    /**
     * @param imageView
     * @param bitmap
     * @category 图片加载效果
     */
    private static void fadeInDisplay(ImageView imageView, Bitmap bitmap, boolean isAnimation) {
        // if (isAnimation) {
        // final TransitionDrawable transitionDrawable = new
        // TransitionDrawable(new Drawable[] { TRANSPARENT_DRAWABLE,
        // new BitmapDrawable(imageView.getResources(), bitmap) });
        // imageView.setImageDrawable(transitionDrawable);
        // imageView.setScaleType(ScaleType.FIT_XY);
        // transitionDrawable.startTransition(500);
        // } else {
        imageView.setScaleType(ScaleType.FIT_XY);
        imageView.setImageBitmap(bitmap);
        // }
    }

    // 外部接口函数
    public void display(ImageView container, ProgressBar pb, String url) {
        container.setTag(pb);
        bitmapUtils.display(container, url, new CustomBitmapLoadCallBack());
    }
}
