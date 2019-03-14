package com.ailsa.example.starfield.music;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.ailsa.example.starfield.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ailsa
 * <p>
 * 2019/3/12 0012
 * <p>
 * MusicView，音量条的具体实现
 */
public class MusicView extends BaseMusicView {
    /**
     * 音量条集合
     */
    private List<SingleVolume> volumes;
    /**
     * 音量数量
     */
    private int volumeCount;
    /**
     * 矩形的宽高及上、左间距
     */
    private int rectHeight;
    private int rectWidth;
    private int rectTopSpace;
    private int rectLeftSpace;
    /**
     * 上一个音量条的右坐标
     */
    private int lastVolumeRight;
    /**
     * 上下方向的音量条颜色
     */
    private int topVolumesColor;
    private int bottomVolumesColor;

    public MusicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MusicView);
        volumeCount = typedArray.getInteger(R.styleable.MusicView_volumeCount, 10);
        sleepTime = typedArray.getInteger(R.styleable.MusicView_sleepTime, 40);
        rectHeight = typedArray.getInteger(R.styleable.MusicView_rectHeight, 20);
        rectWidth = typedArray.getInteger(R.styleable.MusicView_rectWidth, 30);
        rectLeftSpace = typedArray.getInteger(R.styleable.MusicView_rectLeftSpace, 15);
        rectTopSpace = typedArray.getInteger(R.styleable.MusicView_rectTopSpace, 10);
        topVolumesColor = typedArray.getColor(R.styleable.MusicView_topVolumesColor, context.getColor(R.color.colorTopVolumes));
        bottomVolumesColor = typedArray.getColor(R.styleable.MusicView_bottomVolumesColor, context.getColor(R.color.colorBottomVolumes));
        typedArray.recycle();
        volumes = new ArrayList<>();
    }

    @Override
    protected void initVolumes() {
        for (int i = 0; i < volumeCount; i++) {
            SingleVolume volume = new SingleVolume(getHeight(), rectHeight, rectWidth,
                    rectLeftSpace, rectTopSpace, lastVolumeRight,
                    topVolumesColor, bottomVolumesColor);
            lastVolumeRight = volume.getlastVolumeRight();                // 获取音量条的右坐标
            volumes.add(volume);
        }
    }

    @Override
    protected void drawVolumes(Canvas canvas) {
        for (SingleVolume volume : volumes) {
            volume.drawSingleVolume(canvas);
        }
        sleepTime = 500;    // 改变音量条变化速度的参数
    }

    @Override
    protected void moveVolumes() {
        for (SingleVolume volume : volumes) {
            volume.moveSingleVolume();
        }
    }
}
