package com.example.muresand.simpleweatherapp.util;

import android.animation.Animator;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/**
 * Created by muresand on 9/26/2017.
 */

public class AnimationsUtil {

    private AnimationsUtil() {
    }

    public static void animateImageViewWithRotation(ImageView imageView, float angle) {
        imageView.animate().rotation(angle).setStartDelay(100).setDuration(2500).start();
    }

    public static void animateImageViewByScaling(final ImageView imageView, final float scaleFactor) {

        AnimationSet scaleAnimationSet = new AnimationSet(true);
        float pivotX = (float) (imageView.getDrawable().getBounds().width() / 2.0);
        float pivotY = (float) (imageView.getDrawable().getBounds().height() / 2.0);

        ScaleAnimation scaleDownAnimation = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f, pivotX, pivotY);
        scaleDownAnimation.setDuration(1250);

        ScaleAnimation scaleUpAnimation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, pivotX, pivotY);
        scaleUpAnimation.setStartOffset(1250);
        scaleUpAnimation.setDuration(1250);

        scaleAnimationSet.addAnimation(scaleDownAnimation);
        scaleAnimationSet.addAnimation(scaleUpAnimation);

        imageView.startAnimation(scaleAnimationSet);
    }

}
