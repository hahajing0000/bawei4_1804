package com.zy.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * @author:zhangyue
 * @date:2020/9/11
 */
public class ImageLoaderManager {
    private IImageLoader imageLoader;
    private static volatile ImageLoaderManager instance=null;
    private ImageLoaderManager(){
        imageLoader=new GlideImageLoader();
    }
    public static ImageLoaderManager getInstance(){
        if (null==instance){
            synchronized (ImageLoaderManager.class){
                if (null==instance){
                    instance=new ImageLoaderManager();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化ImageLoader
     * @param imageLoader
     */
    public void initImageLoader(IImageLoader imageLoader){
        this.imageLoader=imageLoader;
    }

    /**
     * 加载图片
     * @param context
     * @param url
     * @param imageView
     */
    public void load(Context context, String url, ImageView imageView){
        this.imageLoader.load(context,url,imageView);
    }

}
