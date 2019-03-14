package com.ailsa.example.starfield.music;

import android.graphics.Canvas;

/**
 * @author ailsa
 * <p>
 * 2019/3/12 0012
 * <p>
 * IAnimation，处理音量条的接口
 */
public interface IAnimation {
    /**
     * 绘制单个音量条
     */
    void drawSingleVolume(Canvas canvas);

    /**
     * 移动单个音量条
     */
    void moveSingleVolume();
}
