package com.ailsa.example.starfield.music;

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
 * BaseMusicView，音量效果的基础View
 */
public abstract class BaseMusicView extends View {
    /**
     * 自定义线程，实现音量条的移动效果
     */
    private MThread thread;
    /**
     * Thread睡眠时间
     */
    protected int sleepTime;

    public BaseMusicView(Context context) {
        super(context);
    }

    public BaseMusicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        sleepTime = 30;
    }

    /**
     * 初始化所有音量条，子类实现
     */
    protected abstract void initVolumes();

    /**
     * 绘制所有音量条，子类实现
     *
     * @param canvas 画布
     */
    protected abstract void drawVolumes(Canvas canvas);

    /**
     * 移动所有音量条，子类实现
     */
    protected abstract void moveVolumes();

    @Override
    protected void onDraw(Canvas canvas) {
        if (thread == null) {
            initVolumes();            // 初始化所有音量条
            thread = new MThread();
            thread.start();
        } else {
            drawVolumes(canvas);      // 绘制所有音量条
        }
    }

    class MThread extends Thread {
        @Override
        public void run() {
            while (true) {
                moveVolumes();               // 移动所有音量条
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
