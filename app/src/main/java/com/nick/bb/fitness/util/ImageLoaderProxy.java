package com.nick.bb.fitness.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.nick.bb.fitness.R;

public class ImageLoaderProxy {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private ImageLoaderProxy() {
    }

    private static class GlideControlHolder {
        private static ImageLoaderProxy instance = new ImageLoaderProxy();
    }

    public static ImageLoaderProxy getInstance() {
        return GlideControlHolder.instance;
    }

    // 将资源ID转为Uri
    public Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }

    // 加载drawable图片
    public void loadResImage(Context context, int resId, ImageView imageView) {
        Glide.with(context)
                .load(resourceIdToUri(context, resId))
                .placeholder(R.mipmap.default_img_bg)
                .error(R.mipmap.default_img_bg)
                .crossFade()
                .into(imageView);
    }

    public void loadResImage2(Context context, int resId, ImageView imageView) {
        Glide.with(context)
                .load(resourceIdToUri(context, resId))
                .crossFade()
                .into(imageView);
    }

    // 加载drawable图片
    public void loadResImage(Context context, int resId, ImageView imageView, int defaultId) {
        Glide.with(context)
                .load(resourceIdToUri(context, resId))
                .placeholder(defaultId)
                .error(defaultId)
                .crossFade()
                .into(imageView);
    }

    // 加载本地图片
    public void loadLocalImage(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load("file://" + path)
                .placeholder(R.mipmap.default_img_bg)
                .error(R.mipmap.default_img_bg)
                .crossFade()
                .into(imageView);
    }
    // 加载网络图片
    public void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .animate(R.anim.image_load)
                .placeholder(R.mipmap.default_img_bg)
                .error(R.mipmap.default_img_bg)
                .crossFade()
                .into(imageView);
    }
    public void loadImageCF(Context context, String url, ImageView imageView,FormatType formatType) {
        DrawableRequestBuilder builder = Glide.with(context)
                .load(url)
                .animate(R.anim.image_load)
                .placeholder(R.mipmap.default_img_bg)
                .error(R.mipmap.default_img_bg);
        switch (formatType){
            case CenterCrop:
                builder.centerCrop();
                break;
            case CrossFade:
                builder.crossFade();
                break;
            case FitCenter:
                builder.fitCenter();
                break;
        }
        builder.into(imageView);

    }
    public enum FormatType{
        CenterCrop,
        CrossFade,
        FitCenter
    }
}
