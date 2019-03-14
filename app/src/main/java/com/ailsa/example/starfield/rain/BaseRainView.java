package com.ailsa.example.starfield.rain;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author ailsa
 * <p>
 * 2019/3/7 0007
 * <p>
 * BaseRainView，下雨效果的基础View
 */
public abstract class BaseRainView extends View {
    /**
     * 自定义线程，实现雨的移动效果
     */
    private MThread thread;

    public BaseRainView(Context context) {
        super(context);
    }

    public BaseRainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 初始化所有雨点，子类实现
     */
    protected abstract void initRainDrops();

    /**
     * 绘制所有雨点，子类实现
     *
     * @param canvas 画布
     */
    protected abstract void drawRainDrops(Canvas canvas);

    /**
     * 移动所有雨点，子类实现
     */
    protected abstract void moveRainDrops();

    @Override
    protected void onDraw(Canvas canvas) {
        if (thread == null) {
            initRainDrops();            // 初始化所有雨点
            thread = new MThread();
            thread.start();
        } else {
            drawRainDrops(canvas);      // 绘制所有雨点
        }
    }

    class MThread extends Thread {
        @Override
        public void run() {
            while (true) {
                moveRainDrops();            // 移动所有雨点
                postInvalidate();           // 调用onDraw()重绘
                try {
                    Thread.sleep(30); // 休眠30ms后再次执行移动逻辑
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
