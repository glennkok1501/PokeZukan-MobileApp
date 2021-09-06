package com.gmail.pokedex.Main.Adapters;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.gmail.pokedex.R;

public class ImageScaleAnim {
    private Animation big, small;

    public ImageScaleAnim(Context context) {
        big = AnimationUtils.loadAnimation(context, R.anim.increase_size_anim);
        small = AnimationUtils.loadAnimation(context, R.anim.decrease_size_anim);
    }

    public void set(View view, ImageView img){
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    img.startAnimation(big);
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    img.startAnimation(small);
                }
                return false;
            }
        });
    }
}
