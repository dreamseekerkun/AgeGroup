package com.lbadvisor.AgeGroup.utils;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 倒计时类
 */
public class CountDownTimer {

    private long hours;
    private String hour;
    private long minutes;
    private String minute;
    private long seconds;
    private String second;
    private long diff;
    private long days;
    private String day;
    private TextView tvCountDown;
    private String time;
    private static CountDownTimer countDownTimer;

    public static CountDownTimer newInstance(){
        countDownTimer = new CountDownTimer();
        return countDownTimer;
    }

    public void setCountDownTime(TextView tvCountDown, String time){
        this.time = time;
        this.tvCountDown = tvCountDown;
        getTime();
        if (diff > 0) {
            Message message = handler.obtainMessage(1);
            handler.sendMessageDelayed(message, 1000);
        }
    }

    Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    diff = diff - 1000;
                    getShowTime();
                    if (diff > 0) {
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000);
                    } else {
//                        txtView.setVisibility(View.GONE);
                    }
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };


    /**
     * 获取当前时间到截止日期的时间差
     */
    private void getTime() {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sDateFormat.format(new Date());
        try {
            Date d1 = df.parse(time);//截止的日期
            Date d2 = df.parse(date);//当前时间
            diff = d1.getTime() - d2.getTime();
            days = diff / (1000 * 60 * 60 * 24);
            hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            seconds = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / (1000);
            convert();
            if (diff >= 0) {
                tvCountDown.setText("距离活动结束 " + day + hour + ":" + minute + ":" + second);
            }else if(diff <0){
                tvCountDown.setText("距离活动结束 00:00:00");
            }
        } catch (Exception e) {
        }
    }

    /**
     * 每隔一秒不停的展示时间
     */
    private void getShowTime() {
        days = diff / (1000 * 60 * 60 * 24);
        hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
        seconds = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / (1000);
        convert();
        tvCountDown.setText("距离活动结束 " + day + hour + ":" + minute + ":" + second);
    }

    private void convert() {
        if (0 < days && days < 10) {
            day = "0" + days + ":";
        } else if (days == 0) {
            day = "";
        } else {
            day = Long.toString(days);
        }
        if (hours < 10) {
            hour = "0" + hours;
        } else {
            hour = Long.toString(hours);
        }
        if (minutes < 10) {
            minute = "0" + minutes;
        } else {
            minute = Long.toString(minutes);
        }
        if (seconds < 10) {
            second = "0" + seconds;
        } else {
            second = Long.toString(seconds);
        }
    }
}  
