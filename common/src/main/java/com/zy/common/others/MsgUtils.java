package com.zy.common.others;

import android.content.Context;
import android.widget.Toast;

/**
 * @author:zhangyue
 * @date:2020/9/18
 */
public class MsgUtils {
    public static void showMsg(Context context, String msg){
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
