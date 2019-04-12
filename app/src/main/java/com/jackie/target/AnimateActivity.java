package com.jackie.target;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimateActivity extends BaseActivity {
    @BindView(R.id.iv_img)
    ImageView img1;
    @BindView(R.id.iv_img2)
    ImageView img2;

    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);

        img1.setBackgroundResource(R.drawable.value_ani);
        animationDrawable = (AnimationDrawable) img1.getBackground();
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "show", Toast.LENGTH_LONG).show();
                animationDrawable.start();
            }
        });


        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.animation);

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img2.startAnimation(animation);
            }
        });
    }
}
