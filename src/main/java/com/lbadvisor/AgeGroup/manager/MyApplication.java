package com.lbadvisor.AgeGroup.manager;

import android.app.Application;
import android.os.Handler;
import com.lbadvisor.AgeGroup.network.HttpTool;

/**
 * Created by likun on 15/9/27.
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;
    private static Handler handler;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        handler = new Handler();
        HttpTool.init();
    }

    public static MyApplication getApplication(){
        return myApplication;
    }

    /**
     * get the main hanlder
     * @return Handler
     */
    public static Handler getHandler() {
        return handler;
    }
}
