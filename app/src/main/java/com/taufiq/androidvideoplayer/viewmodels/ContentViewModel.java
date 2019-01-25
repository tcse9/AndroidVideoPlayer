package com.taufiq.androidvideoplayer.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.taufiq.androidvideoplayer.core.ApplicationSingleton;
import com.taufiq.androidvideoplayer.listeners.IVideoViewActionListener;
import com.taufiq.androidvideoplayer.modules.VideoModule;

import javax.inject.Inject;

public class ContentViewModel extends AndroidViewModel {

    @Inject
    VideoModule videoModule;

    /**
     * View model constructor
     * @param application of type {@link Application}
     */
    public ContentViewModel(Application application){
        super(application);

        //Ijects VideoModule
        ApplicationSingleton.getInstance().getBaseComponents().inject(this);


    }


    /**
     * Assign a {@link IVideoViewActionListener}
     */

    public IVideoViewActionListener mVideoViewListener = new IVideoViewActionListener() {
        @Override
        public void onTimeBarSeekChanged(boolean ffwdrwd) {

            videoModule.setOnForwardBackwardCall(ffwdrwd);

        }

        @Override
        public void onResume() {
            Log.e("*** PLAY", "playing");
            videoModule.setOnPausedCalled(false);
        }

        @Override
        public void onPause() {
            Log.e("*** PAUSE", "paused");
            videoModule.setOnPausedCalled(true);
        }

    };

    /**
     * Returns the forward/backward callback
     * @return
     */
    public MutableLiveData<Boolean> getOnForwardBackwardCallObservable() {
        return videoModule.getOnForwardBackwardCall();
    }

    /**
     * Returns the paused/play callback
     * @return
     */
    public MutableLiveData<Boolean> getOnPausedCalledObservable() {
        return videoModule.getOnPausedCalled();
    }
}
