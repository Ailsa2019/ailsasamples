package com.ailsa.example.starfield.rain;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ailsa.example.starfield.R;

/**
 * @author ailsa
 * <p>
 * 2019/03/07 0007
 * <p>
 * RainActivity，下雨效果展示界面，交替展示垂直下落和倾斜下落效果
 */
public class RainActivity extends AppCompatActivity {
    /**
     * 垂直下落的雨
     */
    private FallsDownRainView fallsDownRainView;
    /**
     * 倾斜下落的雨
     */
    private TiltDownRainView tiltDownRainView;
    /**
     * Handler实现每隔3s改变一次雨点下落的方向
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (fallsDownRainView.isShown()) {
                fallsDownRainView.setVisibility(View.GONE);
                tiltDownRainView.setVisibility(View.VISIBLE);
            } else {
                fallsDownRainView.setVisibility(View.VISIBLE);
                tiltDownRainView.setVisibility(View.GONE);
            }
            sendEmptyMessageDelayed(1, 3000);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rain);
        initViews();
        handler.sendEmptyMessageDelayed(1, 1000);
    }

    /**
     * 初始化view
     */
    private void initViews() {
        fallsDownRainView = findViewById(R.id.falls_down_rain_view);
        tiltDownRainView = findViewById(R.id.tilt_down_rain_view);
    }
}
