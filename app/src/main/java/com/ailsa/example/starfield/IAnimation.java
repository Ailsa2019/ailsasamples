package com.ailsa.example.starfield;

import android.graphics.Canvas;

/**
 * @author ailsa
 * <p>
 * 2019/3/12 0012
 * <p>
 * IAnimation，处理粒子的接口
 */
public interface IAnimation {
    /**
     * 绘制单个粒子
     */
    void drawSingleParticle(Canvas canvas);

    /**
     * 移动单个粒子
     */
    void moveSingleParticle();
}
