package com.jackie.target;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Path;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimateActivity extends BaseActivity {
    @BindView(R.id.iv_img)
    ImageView img1;
    @BindView(R.id.iv_img2)
    ImageView img2;
    @BindView(R.id.loading_spinner)
    ProgressBar loadingSpinner;

    AnimationDrawable animationDrawable;
    final int duration = 5000;
    int width;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);

        img1.setBackgroundResource(R.drawable.value_ani);
        animationDrawable = (AnimationDrawable) img1.getBackground();
        width = img2.getLayoutParams().width;
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
//                img2.startAnimation(animation);
//                crossfade();
//                objectAnim();
//                animateShow();
                ViewWrapper wrapper = new ViewWrapper(img2);
                ObjectAnimator animator = ObjectAnimator.ofInt(wrapper,"width",width,1);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
        img2.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator.setDuration(duration).start();
            }
        });



    }
    private void objectAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(img2, "translationY", 100f);
        animator.setDuration(duration);
        Path path = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            path = new Path();
            path.moveTo(100f,100f);

//            path.arcTo(0f, 0f, 1f, 1f, 270, -180f, true);
        }
        PathInterpolator pathInterpolator = new PathInterpolator(0f,0f,0.5f,0.5f);
        animator.setInterpolator(pathInterpolator);
        animator.start();
    }

    private void animateShow(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,img2.getMeasuredHeight());
        valueAnimator.setDuration(duration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams params = img2.getLayoutParams();
                params.height = (int) animation.getAnimatedValue();
                img2.setLayoutParams(params);
            }
        });
        valueAnimator.start();
    }
    private void animateHide(){

    }
    private void crossfade(){
        img2.setAlpha(0f);
        loadingSpinner.setAlpha(1f);
        img2.setVisibility(View.VISIBLE);
        loadingSpinner.setVisibility(View.VISIBLE);
        img2.animate()
                .alpha(1f)
                .setDuration(duration);
        loadingSpinner.animate()
                .alpha(0f)
                .setDuration(duration)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loadingSpinner.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }

    private static class ViewWrapper{
        private View target;
        public ViewWrapper(View target){
            this.target = target;
        }
        public int getHeight(){
            return target.getLayoutParams().height;
        }
        public int getWidth(){
            return target.getLayoutParams().width;
        }
        public void setHeight(int hei){
            target.getLayoutParams().height = hei;
            target.requestLayout();
        }
        public void setWidth(int w){
            target.getLayoutParams().width = w;
            target.requestLayout();
        }
    }
}
