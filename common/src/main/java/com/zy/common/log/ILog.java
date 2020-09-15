package com.zy.common.log;

/**
 * @author:zhangyue
 * @date:2020/9/14
 */
public interface ILog {
    void writeLog(LogEnum logEnum,String log);
    void writeLog(LogEnum logEnum,String tag,String log);
}
