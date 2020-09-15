package com.zy.common.log;

import android.util.Log;

import com.zy.common.BuildConfig;

/**
 * @author:zhangyue
 * @date:2020/9/14
 */
public final class LogUtils {
    private String TAG="hahajing";
    private Boolean isDebug= BuildConfig.isDebug;

    private static LogUtils instance=new LogUtils();
    private LogUtils(){}
    public static LogUtils getInstance(){
        return instance;
    }

    public void d(String log){
        if (isDebug){
            Log.d(TAG,"DEBUG INFO -> "+log);
        }

    }

    public void d(String tag,String log){
        if (isDebug){
            Log.d(TAG,"DEBUG INFO -> Tag:"+tag+" Info:"+log);
        }
    }

    public void e(String log){
        if (isDebug){
            Log.e(TAG,"DEBUG INFO -> "+log);
        }
    }

    public void e(String tag,String log){
        if (isDebug){
            Log.e(TAG,"DEBUG INFO -> Tag:"+tag+" Info:"+log);
        }
    }


    public void w(String log){
        if (isDebug){
            Log.w(TAG,"DEBUG INFO -> "+log);
        }
    }

    public void w(String tag,String log){
        if (isDebug){
            Log.w(TAG,"DEBUG INFO -> Tag:"+tag+" Info:"+log);
        }
    }


    public void i(String log){
        if (isDebug){
            Log.i(TAG,"DEBUG INFO -> "+log);
        }
    }

    public void i(String tag,String log){
        if (isDebug){
            Log.i(TAG,"DEBUG INFO -> Tag:"+tag+" Info:"+log);
        }
    }


}
