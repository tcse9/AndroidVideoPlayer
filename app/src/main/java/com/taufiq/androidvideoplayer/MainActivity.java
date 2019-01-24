package com.taufiq.androidvideoplayer;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;

import com.taufiq.androidvideoplayer.binders.UiManager;
import com.taufiq.androidvideoplayer.core.ApplicationSingleton;
import com.taufiq.androidvideoplayer.databinding.ActivityMainBinding;
import com.taufiq.androidvideoplayer.modules.VideoModule;
import com.taufiq.androidvideoplayer.utils.Constants;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UiManager uiManager;
    private int position = 0;
    private MediaController mediaController;
    @Inject
    VideoModule videoModule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        uiManager = new UiManager();
        binding.setUimanager(uiManager);


        init();
    }


    /**
     * Initialization starts from here
     */
    private void init(){

        ApplicationSingleton.getInstance().getBaseComponents().inject(this);


        if (mediaController == null) {
            mediaController = new MediaController(MainActivity.this);
        }

        binding.videoView.setVideoViewListener(videoModule.getVideoViewListener());


        videoModule.setPrepare(this, uiManager, binding, mediaController, position);

        videoModule.getSeekTimeLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer currentTime) {

            }
        });


    }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //we use onSaveInstanceState in order to store the video playback position for orientation change
        savedInstanceState.putInt(Constants.SEEK_POSITION, binding.videoView.getCurrentPosition());
        binding.videoView.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //we use onRestoreInstanceState in order to play the video playback from the stored position
        position = savedInstanceState.getInt(Constants.SEEK_POSITION);
        binding.videoView.seekTo(position);
    }
}
