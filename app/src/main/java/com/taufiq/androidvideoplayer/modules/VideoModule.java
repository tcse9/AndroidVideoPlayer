package com.taufiq.androidvideoplayer.modules;

import android.arch.lifecycle.MutableLiveData;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class VideoModule {


    private MutableLiveData<Boolean> onForwardBackwardCall = new MutableLiveData<>();
    private MutableLiveData<Boolean> onPausedCalled = new MutableLiveData<>();


    /**
     * Initialization
     * @return
     */
    @Provides
    @Singleton
    public VideoModule init(){

        return this;

    }


    /**
     * Returns whether the controller button pressed is forward or backward
     * @return
     */
    public MutableLiveData<Boolean> getOnForwardBackwardCall() {
        return onForwardBackwardCall;
    }

    /**
     * Returns whether the pause/play button is pressed
     * @return
     */
    public MutableLiveData<Boolean> getOnPausedCalled() {
        return onPausedCalled;
    }

    /**
     * Sets the boolean value of forward/backward call
     * @param ffwdrwd
     */
    public void setOnForwardBackwardCall(boolean ffwdrwd) {
       onForwardBackwardCall.setValue(ffwdrwd);
    }

    /**
     * Sets the boolean value of paused/play call
     * @param isPaused
     */
    public void setOnPausedCalled(boolean isPaused) {
        onPausedCalled.setValue(isPaused);
    }
}



