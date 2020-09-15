package com.zy.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * @author:zhangyue
 * @date:2020/9/11
 */
public interface IImageLoader {
    /**
     * 加载图片
     * @param context
     * @param url
     * @param imageView
     */
    void load(Context context, String url, ImageView imageView);
}
