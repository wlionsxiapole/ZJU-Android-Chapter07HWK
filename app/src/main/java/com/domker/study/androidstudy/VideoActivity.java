package com.domker.study.androidstudy;

import android.content.res.Configuration;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * 使用系统VideoView播放 resource 视频
 */
public class VideoActivity extends AppCompatActivity {
    private Button buttonPlay;
    private Button buttonPause;
    private VideoView videoView;
    private boolean portrait;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_video);
        setTitle("VideoView");

        videoView = findViewById(R.id.videoView);
        Uri uri = Uri.parse("http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4");
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.requestFocus();


        buttonPause = findViewById(R.id.buttonPause);
        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.pause();
            }
        });

        buttonPlay = findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
            }
        });

        videoView = findViewById(R.id.videoView);
        videoView.setVideoPath(getVideoPath(R.raw.bytedance));
    }

    private String getVideoPath(int resId) {
        return "android.resource://" + this.getPackageName() + "/" + resId;
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        portrait = (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT ) ;
        tryFullScreen(!portrait);
    }

    private void tryFullScreen(boolean fullScreen) {
        if( VideoActivity.this instanceof AppCompatActivity) {
            ActionBar supportActionBar = ((AppCompatActivity) VideoActivity.this).getSupportActionBar();

            if(supportActionBar != null) {
                if (fullScreen) {
                    supportActionBar.hide();
                } else {
                    supportActionBar.show();
                }
            }
        }
        setFullScreen(fullScreen);
    }

    private void setFullScreen(boolean fullScreen) {
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        android.view.Display display = wm.getDefaultDisplay();
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        if(fullScreen) {
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            Point size = new Point();
            display.getSize(size);
            float height = size.y;
            float width = size.x;
            videoView.getLayoutParams().height = (int) width;
            videoView.getLayoutParams().width = (int) height;
        }else {
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

            Point size = new Point();
            display.getSize(size);
            float height = size.y;
            float width = size.x;
            videoView.getLayoutParams().height = (int) height;
            videoView.getLayoutParams().width = (int) width;
        }
    }

}