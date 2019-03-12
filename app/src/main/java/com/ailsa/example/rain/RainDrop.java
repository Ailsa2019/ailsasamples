package com.ailsa.example.rain;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

/**
 * @author ailsa
 * <p>
 * 2019/3/7 0007
 * <p>
 * RainDrop，单个雨点的实现
 */
public class RainDrop {
    /**
     * 屏幕宽高
     */
    private int height;
    private int width;
    /**
     * 画笔在画布x/y方向的起始、终止位置及速度
     */
    private int startX;
    private int startY;
    private int stopX;
    private int stopY;
    private float speed;
    /**
     * 线条在x/y方向的偏移量
     */
    private int offsetX;
    private int offsetY;
    /**
     * 画笔及画笔颜色
     */
    private Paint paint;
    private int color;
    /**
     * 随机数
     */
    private Random random;
    /**
     * 是否随机生成雨点颜色
     */
    private boolean isRandColor;

    /**
     * 雨点垂直下落效果
     *
     * @param height      屏幕高度
     * @param width       屏幕宽度
     * @param offsetY     雨点在Y方向的偏移量
     * @param color       雨点颜色
     * @param isRandColor 是否随机生成雨点颜色
     */
    public RainDrop(int height, int width, int offsetY, int color, boolean isRandColor) {
        this.height = height;
        this.width = width;
        offsetX = 0;
        this.offsetY = offsetY;
        this.color = color;
        this.isRandColor = isRandColor;
        init();
    }

    /**
     * 雨点倾斜下落效果
     *
     * @param height      屏幕高度
     * @param width       屏幕宽度
     * @param offsetX     雨点在X方向的偏移量
     * @param offsetY     雨点在Y方向的偏移量
     * @param color       雨点颜色
     * @param isRandColor 是否随机生成雨点颜色
     */
    public RainDrop(int height, int width, int offsetX, int offsetY, int color, boolean isRandColor) {
        this.height = height;
        this.width = width;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.color = color;
        this.isRandColor = isRandColor;
        init();
    }

    /**
     * 初始化所有参数
     */
    private void init() {
        // 避免多次初始化，new出多个实例
        random = new Random();
        paint = new Paint();
        if (isRandColor) {
            int a = random.nextInt(255);
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            paint.setARGB(a, r, g, b);
        } else {
            paint.setColor(color);
        }
        initPosition();
    }

    /**
     * 初始化画笔的位置和速度
     */
    private void initPosition() {
        startX = 1 + random.nextInt(width);
        startY = 10 + random.nextInt(height);
        stopX = startX + offsetX;
        stopY = startY + offsetY;
        speed = 0.2f + random.nextFloat();
    }

    /**
     * 绘制单个雨点
     *
     * @param canvas 画布
     */
    void drawSingleRainDrop(Canvas canvas) {
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    /**
     * 单个雨点的移动逻辑
     */
    void moveSingleRainDrop() {
        startX += offsetX * speed;
        stopX += offsetX * speed;
        startY += offsetY * speed;
        stopY += offsetY * speed;
        if (startY > height) {
            initPosition();
        }
    }
}
