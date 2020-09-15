package com.zy.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author:zhangyue
 * @date:2020/9/11
 */
public class GlideImageLoader implements IImageLoader {
    @Override
    public void load(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }
}
