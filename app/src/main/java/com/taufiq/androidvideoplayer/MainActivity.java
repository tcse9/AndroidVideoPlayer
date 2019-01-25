package com.taufiq.androidvideoplayer;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;

import com.taufiq.androidvideoplayer.binders.UiManager;
import com.taufiq.androidvideoplayer.databinding.ActivityMainBinding;
import com.taufiq.androidvideoplayer.utils.Constants;

import com.taufiq.androidvideoplayer.viewmodels.ContentViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UiManager uiManager;
    private int position = 0;
    private MediaController mediaController;
    private ContentViewModel contentViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        uiManager = new UiManager();
        binding.setUimanager(uiManager);

        contentViewModel = ViewModelProviders.of(this).get(ContentViewModel.class);

        init();
    }





    /**
     * Initialization starts from here
     */
    private void init(){


        if (mediaController == null) {
            mediaController = new MediaController(MainActivity.this);
        }

        setPrepare(this, uiManager, binding, mediaController, position);


        contentViewModel.getOnPausedCalledObservable().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isPaused) {
                if(isPaused){
                    Toast.makeText(MainActivity.this, getString(R.string.paused), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, getString(R.string.play), Toast.LENGTH_SHORT).show();
                }
            }
        });

        contentViewModel.getOnForwardBackwardCallObservable().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean ffwdrwd) {
                if(ffwdrwd){
                    Toast.makeText(MainActivity.this, getString(R.string.backward), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, getString(R.string.forward), Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


    /**
     * Setup the whole {@link android.widget.VideoView} preparation and loading process
     * @param context
     * @param uiManager
     * @param binding
     * @param mediaController
     * @param position
     * @return
     */
    public void setPrepare(Context context, final UiManager uiManager, final ActivityMainBinding binding, MediaController mediaController, final int position){
        try {
            binding.videoView.setMediaController(mediaController);
            binding.videoView.setVideoPath("android.resource://" + context.getPackageName() + "/" + R.raw.bunny);
            binding.videoView.requestFocus();
            binding.videoView.start();
            binding.videoView.setVideoViewListener(contentViewModel.mVideoViewListener);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        uiManager.setLoadingProgressBarVisibility(View.VISIBLE);

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
