package com.ly.moveview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ly on 2019/6/25 14:36
 * <p>
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
public class MyView extends ViewGroup {


    public MyView(Context context) {
        super(context);
        setMove();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setMove();

    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setMove();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setMove();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("lylogP", "onDraw");
        // 创建画笔
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setStrokeWidth(5);

        p.setColor(Color.GREEN);// 设置绿色
        canvas.drawLine(0, 0, 70, 0, p);// 画线
        canvas.drawLine(130, 0, 200, 0, p);// 画线

        canvas.drawLine(200, 0, 200, 70, p);// 画线
        canvas.drawLine(200, 130, 200, 200, p);// 画线
        canvas.drawLine(200, 200, 130, 200, p);// 画线
        canvas.drawLine(0, 200, 70, 200, p);// 画线

        canvas.drawLine(0, 0, 0, 70, p);// 画线
        canvas.drawLine(0, 130, 0, 200, p);// 画线


        canvas.drawCircle(100,0,30,p);
        canvas.drawCircle(200,100,30,p);
        canvas.drawCircle(100,200,30,p);
        canvas.drawCircle(0,100,30,p);

        p.setColor(Color.WHITE);
        canvas.drawCircle(100,0,25,p);
        canvas.drawCircle(200,100,25,p);
        canvas.drawCircle(100,200,25,p);
        canvas.drawCircle(0,100,25,p);



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
        Log.d("lylogP", "widthMeasureSpec = " + w);
        Log.d("lylogP", "heightMeasureSpec = " + h);
    }

    @Override
    protected void onLayout(boolean b, int o, int i1, int i2, int i3) {
        Log.d("lylogP", "onLayout x0 = " + o + " y0 =" + i1 + " x1 =" + i2 + " y1 =" + i3 + " getChildCount() =" + getChildCount());
        for (int i = 0, size = getChildCount(); i < size; i++) {
            View view = getChildAt(i);
            // 放置子View，宽高都是100
            view.layout(100, 100, 200, 200);
        }
    }

    int markX = 0, markY = 0;

    public void setMove() {
        this.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //markX,markY建议在类中声明作为全局变量
                Log.d("lylog", " MotionEvent event = " + event.getAction());
                int ev = event.getAction();
                switch (ev) {
                    //按下时
                    case MotionEvent.ACTION_DOWN:
                        //获取初始位置
                        Log.d("lylog", " ACTION_DOWN");
                        markX = (int) event.getRawX();
                        markY = (int) event.getRawY();
                        break;
                    //移动时
                    case MotionEvent.ACTION_MOVE:
                        //求偏移量
                        int dx = (int) event.getRawX() - markX;
                        int dy = (int) event.getRawY() - markY;
                        int left = v.getLeft() + dx;
                        int top = v.getTop() + dy;
                        int right = v.getRight() + dx;
                        int bottom = v.getBottom() + dy;
                        //设置位置
                        Log.d("lylog", " ACTION_MOVE");
                        v.layout(left, top, right, bottom);
                        //重置初始位置
                        markX = (int) event.getRawX();
                        markY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("lylog", " ACTION_UP");
                        break;
                }
                return false;
            }
        });
    }
}
