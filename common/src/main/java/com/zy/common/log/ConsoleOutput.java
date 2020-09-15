package com.zy.common.log;

import android.util.Log;

/**
 * @author:zhangyue
 * @date:2020/9/14
 */
public class ConsoleOutput implements ILog {
    @Override
    public void writeLog(LogEnum logEnum, String log) {

        switch (logEnum){
            case D:
                System.out.println("Debug -> "+log);
                break;
            case E:
                System.out.println("Error -> "+log);
                break;
            case I:
                System.out.println("Info -> "+log);
                break;
            case W:
                System.out.println("Warnning -> "+log);
                break;
            default:
                System.out.println("Default -> "+log);
                break;
        }
    }

    @Override
    public void writeLog(LogEnum logEnum, String tag, String log) {
        switch (logEnum){
            case D:
                System.out.println("Debug: tag:"+tag+"  log:"+log);
                break;
            case E:
                System.out.println("Error tag:"+tag+"  log:"+log);
                break;
            case I:
                System.out.println("Info tag:"+tag+"  log:"+log);
                break;
            case W:
                System.out.println("Warnning tag:"+tag+"  log:"+log);
                break;
            default:
                System.out.println("Default tag:"+tag+"  log:"+log);
                break;
        }
    }
}
