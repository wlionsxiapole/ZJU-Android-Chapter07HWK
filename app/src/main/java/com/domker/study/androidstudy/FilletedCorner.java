package com.domker.study.androidstudy;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

import java.security.MessageDigest;

public class FilletedCorner implements Transformation<Bitmap> {

    private BitmapPool mBitmapPool;

    private float radius;

    private boolean exceptLeftTop, exceptRightTop, exceptLeftBottom, exceptRightBottom;


    @NonNull
    @Override
    public Resource<Bitmap> transform(@NonNull Context context, @NonNull Resource<Bitmap> resource, int outWidth, int outHeight) {
        return null;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}