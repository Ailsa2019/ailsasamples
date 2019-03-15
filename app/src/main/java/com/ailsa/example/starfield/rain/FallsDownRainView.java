package com.ailsa.example.starfield.rain;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.ailsa.example.starfield.BaseView;
import com.ailsa.example.starfield.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ailsa
 * <p>
 * 2019/3/7 0007
 * <p>
 * RainView，下雨效果的具体实现 [ 垂直 ]
 */
public class FallsDownRainView extends BaseView {
    /**
     * 雨点集合
     */
    private List<RainDrop> rainDrops;
    /**
     * 雨点数量、颜色、是否随机生成颜色
     */
    private int rainDropCount;
    private int rainDropColor;
    private boolean isRandColor;
    /**
     * 雨点在X、Y方向的偏移量
     */
    private int offsetY;

    public FallsDownRainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FallsDownRainView);
        rainDropCount = typedArray.getInteger(R.styleable.FallsDownRainView_fallCount, 30);
        rainDropColor = typedArray.getInteger(R.styleable.FallsDownRainView_fallColor, context.getColor(R.color.colorRainDrop));
        isRandColor = typedArray.getBoolean(R.styleable.FallsDownRainView_fallRandColor, false);
        offsetY = typedArray.getInteger(R.styleable.FallsDownRainView_fallOffsetY, 20);
        typedArray.recycle();
        rainDrops = new ArrayList<>();
    }

    @Override
    protected void initParticles() {
        for (int i = 0; i < rainDropCount; i++) {
            rainDrops.add(new RainDrop(getHeight(), getWidth(), offsetY,
                    rainDropColor, isRandColor));
        }
    }

    @Override
    protected void drawParticles(Canvas canvas) {
        for (RainDrop rainDrop : rainDrops) {
            rainDrop.drawSingleParticle(canvas);
        }
    }

    @Override
    protected void moveParticles() {
        for (RainDrop rainDrop : rainDrops) {
            rainDrop.moveSingleParticle();
        }
    }
}
