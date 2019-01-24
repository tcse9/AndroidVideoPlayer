package com.taufiq.androidvideoplayer;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;

import com.taufiq.androidvideoplayer.binders.UiManager;
import com.taufiq.androidvideoplayer.databinding.ActivityMainBinding;
import com.taufiq.androidvideoplayer.listeners.IVideoViewActionListener;
import com.taufiq.androidvideoplayer.viewmodels.ContentViewModel;

public class MainActivity extends AppCompatActivity {

    private ContentViewModel contentViewModel;
    private ActivityMainBinding binding;
    private UiManager uiManager;
    private int position = 0;
    private MediaController mediaController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        uiManager = new UiManager();
        binding.setUimanager(uiManager);

        contentViewModel = ViewModelProviders.of(this).get(ContentViewModel.class);

        init();
    }


    private void init(){
        if (mediaController == null) {
            mediaController = new MediaController(MainActivity.this);
        }

        binding.videoView.setVideoViewListener(mVideoViewListener);
        uiManager.setLoadingProgressBarVisibility(View.VISIBLE);

        try {
            binding.videoView.setMediaController(mediaController);
            binding.videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.bunny);
            binding.videoView.requestFocus();
            binding.videoView.start();

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        binding.videoView.requestFocus();
        binding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {

                uiManager.setLoadingProgressBarVisibility(View.GONE);

                binding.videoView.seekTo(position);
                if (position == 0) {
                    binding.videoView.start();
                } else {
                    binding.videoView.pause();
                }
            }
        });
    }


    private IVideoViewActionListener mVideoViewListener = new IVideoViewActionListener()
    {
        @Override
        public void onTimeBarSeekChanged(int currentTime)
        {
            //TODO what you want
        }

        @Override
        public void onResume()
        {
            //TODO what you want
        }

        @Override
        public void onPause()
        {
            //TODO what you want
        }
    };
}
