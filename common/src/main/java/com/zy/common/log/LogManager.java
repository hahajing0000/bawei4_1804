package com.zy.common.log;

/**
 * @author:zhangyue
 * @date:2020/9/14
 */
public class LogManager {
    private LogType mLogEnum;
    private ILog mLog;
    public LogManager(LogType logEnum){
        mLogEnum=logEnum;
        switch (mLogEnum){
            case File:
                mLog=new FileOutput();
                break;
            case Logcat:
                mLog=new LogcatOutput();
                break;
            case Console:
                mLog=new ConsoleOutput();
                break;
            default:
                mLog=new LogcatOutput();
                break;
        }
    }



    public void writeLog(LogEnum logEnum,String log){
        mLog.writeLog(logEnum,log);
    }

    public void writeLog(LogEnum logEnum,String tag,String log){
        mLog.writeLog(logEnum,tag,log);
    }

    public Builder newBuilder(){
        return new Builder();
    }

    public static class Builder{
        private LogType logEnum;

        public Builder setLogType(LogType _logEnum){
            logEnum=_logEnum;
            return this;
        }

        public LogManager build(){
            return new LogManager(logEnum);
        }
    }
}
