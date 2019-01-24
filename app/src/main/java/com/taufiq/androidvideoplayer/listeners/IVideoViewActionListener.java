package com.taufiq.androidvideoplayer.listeners;

public interface IVideoViewActionListener {

    void onPause();
    void onResume();
    void onTimeBarSeekChanged(int currentTime);
}
