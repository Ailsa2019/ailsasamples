package com.ailsa.example.starfield;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author ailsa
 * <p>
 * 2019/3/12 0012
 * <p>
 * BaseView，粒子效果的基础View
 */
public abstract class BaseView extends View {
    /**
     * 自定义线程，实现粒子的移动效果
     */
    private MThread thread;
    /**
     * Thread睡眠时间
     */
    protected int sleepTime;

    public BaseView(Context context) {
        super(context);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        sleepTime = 30;
    }

    /**
     * 初始化所有粒子 [ 子类实现 ]
     */
    protected abstract void initParticles();

    /**
     * 绘制所有粒子 [ 子类实现 ]
     *
     * @param canvas 画布
     */
    protected abstract void drawParticles(Canvas canvas);

    /**
     * 移动所有粒子 [ 子类实现 ]
     */
    protected abstract void moveParticles();

    @Override
    protected void onDraw(Canvas canvas) {
        if (thread == null) {
            initParticles();            // 初始化所有粒子
            thread = new MThread();
            thread.start();
        } else {
            drawParticles(canvas);      // 绘制所有粒子
        }
    }

    class MThread extends Thread {
        @Override
        public void run() {
            while (true) {
                moveParticles();               // 移动所有粒子
                postInvalidate();           // 调用onDraw()重绘
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
