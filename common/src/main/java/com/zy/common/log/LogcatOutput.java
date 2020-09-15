package com.zy.common.log;

import android.util.Log;

/**
 * @author:zhangyue
 * @date:2020/9/14
 */
public class LogcatOutput implements ILog {
    private String TAG="hahajing";
    @Override
    public void writeLog(LogEnum logEnum, String log) {
        switch (logEnum){
            case D:
                Log.d(TAG,"DEBUG INFO -> "+log);
                break;
            case E:
                Log.d(TAG,"Error INFO -> "+log);
                break;
            case I:
                Log.d(TAG,"Info INFO -> "+log);
                break;
            case W:
                Log.d(TAG,"Warnning INFO -> "+log);
                break;
            default:
                Log.d(TAG,"Default INFO -> "+log);
                break;
        }
    }

    @Override
    public void writeLog(LogEnum logEnum, String tag, String log) {
        switch (logEnum){
            case D:
                Log.d(TAG,"DEBUG INFO Tag:"+tag+"   "+log);
                break;
            case E:
                Log.d(TAG,"Error INFO Tag:"+tag+"   "+log);
                break;
            case I:
                Log.d(TAG,"Info INFO Tag:"+tag+"   "+log);
                break;
            case W:
                Log.d(TAG,"Warnning INFO Tag:"+tag+"   "+log);
                break;
            default:
                Log.d(TAG,"Default INFO Tag:"+tag+"   "+log);
                break;
        }
    }
}
