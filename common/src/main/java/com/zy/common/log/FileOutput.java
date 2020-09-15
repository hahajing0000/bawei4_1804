package com.zy.common.log;

/**
 * @author:zhangyue
 * @date:2020/9/14
 */
public class FileOutput implements ILog {
    @Override
    public void writeLog(LogEnum logEnum, String log) {
        //追加方式写入本地文件
        //本地文件命名使用  yyyyMMdd.log  20200915.log
    }

    @Override
    public void writeLog(LogEnum logEnum, String tag, String log) {

    }
}
