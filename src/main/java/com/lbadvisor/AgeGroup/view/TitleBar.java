package com.lbadvisor.AgeGroup.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lbadvisor.AgeGroup.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by likun on 15/9/27.
 */
public class TitleBar extends RelativeLayout {

    private OnLeftAndRightClickListener listener;//监听点击事件
//    private ImageView leftImage;
//    private ImageView rightImage;
//    private TextView Title;
    private View view;
    @Bind(R.id.iv_leftimage)
    ImageView leftImage;
    @Bind(R.id.iv_rightimage)
    ImageView rightImage;
    @Bind(R.id.tv_title)
    TextView Title;

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.view_titlebar, this);
        ButterKnife.bind(this,view);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        int leftBtnBackground = typedArray.getResourceId(R.styleable.TitleBar_leftbackground, 0);
        int rightBtnBackground = typedArray.getResourceId(R.styleable.TitleBar_rightbackground, 0);
        String titleText = typedArray.getString(R.styleable.TitleBar_titletext);
        float titleTextSize = typedArray.getDimension(R.styleable.TitleBar_titlesize, 14f);
        int titleTextColor = typedArray.getColor(R.styleable.TitleBar_titlecolor, 000000);
        boolean isRightVisible = typedArray.getBoolean(R.styleable.TitleBar_isrightvisible, true);
        typedArray.recycle();//释放资源

//        leftImage = (ImageView) view.findViewById(R.id.iv_leftimage);
//        rightImage = (ImageView) view.findViewById(R.id.iv_rightimage);
        leftImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onLeftButtonClick();

            }
        });
        rightImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onRightButtonClick();
            }
        });
//        Title = (TextView) view.findViewById(R.id.tv_title);
        leftImage.setBackgroundResource(leftBtnBackground);
        rightImage.setBackgroundResource(rightBtnBackground);
        if (isRightVisible) {
            rightImage.setVisibility(View.VISIBLE);
        } else {
            rightImage.setVisibility(View.INVISIBLE);
        }
        Title.setText(titleText);
        Title.setTextSize(titleTextSize);
        Title.setTextColor(titleTextColor);
    }

    //设置监听器
    public void setOnLeftAndRightClickListener(OnLeftAndRightClickListener listener) {
        this.listener = listener;
    }

    //设置左边按钮的可见性
//    public void setLeftButtonVisibility(boolean flag) {
//        if (flag)
//            leftImage.setVisibility(VISIBLE);
//        else
//            leftImage.setVisibility(INVISIBLE);
//    }

    //设置右边按钮的可见性
    public void setRightButtonVisibility(boolean flag) {
        if (flag)
            rightImage.setVisibility(VISIBLE);
        else
            rightImage.setVisibility(INVISIBLE);
    }

    //按钮点击接口
    public interface OnLeftAndRightClickListener {
        void onLeftButtonClick();

        void onRightButtonClick();
    }

//    @Override
//    protected void onDetachedFromWindow() {
//        super.onDetachedFromWindow();
//        ButterKnife.unbind(this,view);
//    }
}
