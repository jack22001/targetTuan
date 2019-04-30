package com.jackie.target.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jackie.target.R;
import com.jackie.target.util.ResourceUtil;

public class PractiseView extends View {
    Paint paint;
    public PractiseView(Context context) {
        super(context);
    }

    public PractiseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PractiseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PractiseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
//        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private void initPaint(){
        paint = new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas) {
//canvas 相当于控件的背景 draw相当于画笔在纸上写东西，画东西
//        canvas.drawCircle(300,300,25,paint);
        super.onDraw(canvas);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);//空心圆
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
//        //画圆
////        canvas.drawCircle(ResourceUtil.dp2px(30),ResourceUtil.dp2px(30),ResourceUtil.dp2px(20),paint);
//        //矩形
//        canvas.drawRect(0,0,ResourceUtil.dp2px(50),ResourceUtil.dp2px(50),paint);
//        paint.setStrokeWidth(1);
//        canvas.drawRect(ResourceUtil.dp2px(60),0,ResourceUtil.dp2px(110),ResourceUtil.dp2px(50),paint);
////        Rect rect = new Rect();
////        RectF rectF = new RectF();
//        //点
//        paint.setStrokeWidth(20);
//        paint.setStrokeCap(Paint.Cap.SQUARE);//(Paint.Cap.ROUND);//(Paint.Cap.BUTT);
//        canvas.drawPoint(100,100,paint);
//        //椭圆
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawOval(150,150,300,400,paint);
//        //划线
//        canvas.drawLine(100,100,200,230,paint);
//        canvas.drawColor(Color.parseColor("#66880000"));
//        //圆角矩形
//        paint.setStrokeWidth(5);
//        canvas.drawRoundRect(150,150,500,500,30,30,paint);
        //弧形，扇形
        canvas.drawArc(150,150,300,300,0,-200,true,paint);
        //text
//        paint.setStrokeWidth(2);
        paint.setTextSize(80);
        //着色器 shader
        Shader shader = new LinearGradient(100,100,150,150,
                Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawText("hello world",0,150,paint);
//        ComposeShader
//        线条宽度 0 和 1 的区别 strokewidth
//        默认情况下，线条宽度为 0，但你会发现，这个时候它依然能够画出线，线条的宽度为 1 像素。那么它和线条宽度为 1
//    有什么区别呢？
//        其实这个和后面要讲的一个「几何变换」有关：你可以为 Canvas 设置 Matrix 来实现几何变换
//    （如放大、缩小、平移、旋转），在几何变换之后 Canvas 绘制的内容就会发生相应变化，包括线条也会加粗，例如 2
//        像素宽度的线条在 Canvas 放大 2 倍后会被以 4 像素宽度来绘制。而当线条宽度被设置为 0 时，它的宽度就被固定为
//    1 像素，就算 Canvas 通过几何变换被放大，它也依然会被以 1 像素宽度来绘制。Google 在文档中把线条宽度为 0 时称作
//    「hairline mode（发际线模式）

//        if()
    }
//绘制子view   针对viewgroup有效
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }
//绘制过程
//   1，背景
//   2，主体（onDraw()）
//   3，子 View（dispatchDraw()）
//   4，滑动边缘渐变和滑动条
//   5，      前景


    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
//    总结
//    1,出于效率的考虑，ViewGroup 默认会绕过 draw() 方法，换而直接执行 dispatchDraw()，以此来简化绘制流程。
//    所以如果你自定义了某个 ViewGroup 的子类（比如 LinearLayout）并且需要在它的除  dispatchDraw()
//    以外的任何一个绘制方法内绘制内容，你可能会需要调用 View.setWillNotDraw(false) 这行代码来切换到完整的绘制流程
//    （是「可能」而不是「必须」的原因是，有些 ViewGroup 是已经调用过 setWillNotDraw(false) 了的，例如 ScrollView）。
//    2,有的时候，一段绘制代码写在不同的绘制方法中效果是一样的，这时你可以选一个自己喜欢或者习惯的绘制方法来重写。
//    但有一个例外：如果绘制代码既可以写在 onDraw() 里，也可以写在其他绘制方法里，那么优先写在 onDraw() ，
//    因为 Android 有相关的优化，可以在不需要重绘的时候自动跳过  onDraw() 的重复执行，以提升开发效率。
//    享受这种优化的只有 onDraw() 一个方法。
}
