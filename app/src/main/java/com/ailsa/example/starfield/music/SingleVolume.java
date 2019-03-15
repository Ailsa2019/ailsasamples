package com.ailsa.example.starfield.music;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.ailsa.example.starfield.IAnimation;

import java.util.Random;

/**
 * @author ailsa
 * <p>
 * 2019/3/12 0012
 * <p>
 * SingleVolume，单个音量条的具体实现
 */
public class SingleVolume implements IAnimation {
    /**
     * 屏幕高度
     */
    private int screenHeight;
    /**
     * 每个矩形的宽度和高度、边距
     */
    private int rectHeight;
    private int rectWidth;
    private int rectTopSpace;
    private int rectLeftSpace;
    /**
     * 竖直方向的矩形个数
     */
    private int verticalCount;
    /**
     * 上一个音量条的右坐标
     */
    private int lastVolumeRight;
    /**
     * 可用于绘制单方向音量条的高度
     */
    private int availableHeight;
    /**
     * 计算向上方向矩形top、bottom坐标的基数
     */
    private int maxTop;
    /**
     * 计算向下方向矩形top、bottom坐标的基数
     */
    private int maxBottom;
    /**
     * 画笔
     */
    private Paint paint;
    /**
     * 随机数
     */
    private Random random;
    /**
     * 上下方向的音量条的颜色
     */
    private int topVolumesColor;
    private int bottomVolumesColor;

    /**
     * 构造函数
     *
     * @param screenHeight    屏幕高度
     * @param rectHeight      矩形高度
     * @param rectWidth       矩形宽度
     * @param rectLeftSpace   矩形左间距
     * @param rectTopSpace    矩形上间距
     * @param lastVolumeRight 上一个矩形的右坐标 [ 绘制水平方向音量条需要 ]
     */
    SingleVolume(int screenHeight, int rectHeight, int rectWidth,
                 int rectLeftSpace, int rectTopSpace, int lastVolumeRight) {
        this.screenHeight = screenHeight;
        this.rectHeight = rectHeight;
        this.rectWidth = rectWidth;
        this.rectLeftSpace = rectLeftSpace;
        this.rectTopSpace = rectTopSpace;
        this.lastVolumeRight = lastVolumeRight;
        topVolumesColor = 0xffffffff;
        bottomVolumesColor = 0xff0000ff;
        init();
    }

    /**
     * 构造函数
     *
     * @param screenHeight    屏幕高度
     * @param rectHeight      矩形高度
     * @param rectWidth       矩形宽度
     * @param rectLeftSpace   矩形左间距
     * @param rectTopSpace    矩形上间距
     * @param lastVolumeRight 上一个矩形的右坐标 [ 绘制水平方向音量条需要 ]
     */
    SingleVolume(int screenHeight, int rectHeight, int rectWidth,
                 int rectLeftSpace, int rectTopSpace, int lastVolumeRight,
                 int topVolumesColor, int bottomVolumesColor) {
        this.screenHeight = screenHeight;
        this.rectHeight = rectHeight;
        this.rectWidth = rectWidth;
        this.rectLeftSpace = rectLeftSpace;
        this.rectTopSpace = rectTopSpace;
        this.lastVolumeRight = lastVolumeRight;
        this.topVolumesColor = topVolumesColor;
        this.bottomVolumesColor = bottomVolumesColor;
        init();
    }

    int getlastVolumeRight() {
        return lastVolumeRight + rectLeftSpace + rectWidth;
    }

    @Override
    public void drawSingleParticle(Canvas canvas) {
        int rectNum = 0;
        for (int i = 0; i < verticalCount; i++) {
            paint.setColor(topVolumesColor);
            int left = rectLeftSpace + lastVolumeRight;
            int top = maxTop - rectNum * (rectHeight + rectTopSpace);
            int right = lastVolumeRight + rectLeftSpace + rectWidth;
            int bottom = maxTop - ((rectNum + 1) * rectHeight + rectNum * rectTopSpace);
            canvas.drawRect(left, top, right, bottom, paint);

            int mirrorTop = maxBottom + rectNum * (rectHeight + rectTopSpace);
            int mirrorBottom = maxBottom + ((rectNum + 1) * rectHeight + rectNum * rectTopSpace);
            paint.setColor(bottomVolumesColor);
            canvas.drawRect(left, mirrorTop, right, mirrorBottom, paint);
            rectNum++;
        }
    }

    @Override
    public void moveSingleParticle() {
        initVerticalCount();
    }

    /**
     * 初始化参数值
     */
    private void init() {
        paint = new Paint();
        random = new Random();
        availableHeight = screenHeight / 2 - 10;    // 可用于绘制单方向音量条的高度
        maxTop = screenHeight / 2 - 5;              // 计算向上方向矩形top、bottom坐标的基数
        maxBottom = screenHeight / 2 + 5;           // 计算向下方向矩形top、bottom坐标的基数
        initVerticalCount();
    }

    /**
     * 初始化音量条的矩形个数
     */
    private void initVerticalCount() {
        verticalCount = (availableHeight - random.nextInt(availableHeight)) / (rectHeight + rectTopSpace);
    }
}
