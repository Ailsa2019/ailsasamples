package com.ailsa.example.starfield;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ailsa.example.starfield.music.MusicActivity;
import com.ailsa.example.starfield.rain.RainActivity;

/**
 * @author ailsa
 * <p>
 * 2019/03/15 0015
 * <p>
 * MainActivity，项目主界面
 */
public class MainActivity extends AppCompatActivity {
    /**
     * 打开下雨和音量效果的按钮
     */
    private Button btnRain;
    private Button btnMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();
    }

    private void initViews() {
        btnRain = findViewById(R.id.btn_rain);
        btnMusic = findViewById(R.id.btn_music);
    }

    private void initEvents() {
        btnRain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RainActivity.class));
            }
        });
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MusicActivity.class));
            }
        });
    }
}
