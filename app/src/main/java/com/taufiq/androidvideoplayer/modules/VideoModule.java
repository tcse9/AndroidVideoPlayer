package com.taufiq.androidvideoplayer.modules;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;

import com.taufiq.androidvideoplayer.R;
import com.taufiq.androidvideoplayer.binders.UiManager;
import com.taufiq.androidvideoplayer.databinding.ActivityMainBinding;
import com.taufiq.androidvideoplayer.listeners.IVideoViewActionListener;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class VideoModule {


    private MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();


    /**
     * Initialization
     * @return
     */
    @Provides
    @Singleton
    public VideoModule init(){

        return new VideoModule();

    }

    /**
     * Assign a {@link IVideoViewActionListener}
     */
    private IVideoViewActionListener mVideoViewListener = new IVideoViewActionListener() {
        @Override
        public void onTimeBarSeekChanged(boolean ffwdrwd) {

            if(ffwdrwd){
                //seeking backwards
                Log.e("*** SEEK", "back");
            }else {
                //seeking forward
                Log.e("*** SEEK", "forw");
            }

        }

        @Override
        public void onResume() {
            Log.e("*** PLAY", "playing");
        }

        @Override
        public void onPause() {
            Log.e("*** PAUSE", "paused");
        }

    };

    /**
     * Returns an {@link IVideoViewActionListener} object
     * @return
     */
    @Provides
    @Singleton
    public IVideoViewActionListener getVideoViewListener() {
        return mVideoViewListener;
    }

    /**
     * Returns the seek time live date (which works as observable)
     * @return
     */
    @Provides
    @Singleton
    public MutableLiveData<Integer> getSeekTimeLiveData() {
        return mutableLiveData;
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
}



