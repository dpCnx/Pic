package com.pic.pic.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

public class GlideUtils {

    public static void setBitmap(final Context context, String url, final ImageView mImageView) {

        Glide.with(context)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        int width = resource.getWidth();
                        int height = resource.getHeight();

                        int width1 = mImageView.getWidth();
                        float sy = (float) (width1 * 0.1) / (float) (width * 0.1);

                        int imageViewHeight = (int) (height * sy);
                        ViewGroup.LayoutParams params = mImageView.getLayoutParams();
                        params.height = imageViewHeight;
                        mImageView.setLayoutParams(params);

                        mImageView.setImageBitmap(resource);
                    }
                });

    }
}
