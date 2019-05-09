package com.jackie.target.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/*
 * 类似linearlayout可以横向放的viewgroup
 * */
public class CustomViewGroup extends ViewGroup {
    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childcount = getChildCount();
        int leftPadding = getPaddingLeft();
        int rightPadding = getPaddingRight();
        int topPadding = getPaddingTop();
        int bottomPadding = getPaddingBottom();

        int width = leftPadding;
        int top = topPadding;
        for (int i = 0; i < childcount; i++) {
            View view = getChildAt(i);
            int viewWidth = view.getMeasuredWidth();
            int viewHeight = view.getMeasuredHeight();
            MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
            int lv = width + params.leftMargin;
            int tv = top + params.topMargin;
            int rv = lv + viewWidth;
            int bv = tv + viewHeight;
            //注意调用的是view.layout方法，layout方法会去调onlayout
            view.layout(lv, tv, rv, bv);

            width = width + viewWidth + params.leftMargin + params.rightMargin;
        }
    }

    //如果想在onmease,onlayout中获取margin参数，重写一下三个方法
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    int lastX = 0;
    int lastY = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                int offX = x-lastX;
                int offY = y-lastY;
                scrollBy(-offX,0);

//用layout方法在viewGroup中是不可行的
//                layout(getLeft()+offX,getTop()+offY,getRight()+offX,getBottom()+offY);
//                for(int i=0;i<getChildCount();i++){
//                    View view = getChildAt(i);
//                    view.layout(getLeft()+offX,getTop(),getRight()+offX,getBottom());
//                }
                break;
        }
        return true;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //1,获取  2，设置，计算 3，保存（setMeaseasureDimension）
//        setMeasuredDimension();
        int width = 0;
        int height = 0;

        //计算每个子view的长宽
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
            int childWidth = view.getMeasuredWidth();
            int childHeight = view.getMeasuredHeight();

            MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
            width = width + childWidth + params.leftMargin + params.rightMargin;
            height = Math.max(height, (childHeight + params.topMargin + params.bottomMargin));
        }
        setMeasuredDimension(width, height);
    }
}
