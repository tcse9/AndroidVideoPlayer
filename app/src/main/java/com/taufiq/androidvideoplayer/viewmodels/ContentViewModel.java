package com.taufiq.androidvideoplayer.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.taufiq.androidvideoplayer.core.ApplicationSingleton;
import com.taufiq.androidvideoplayer.modules.VideoModule;

import javax.inject.Inject;


public class ContentViewModel extends AndroidViewModel {

    @Inject
    VideoModule repositoryModule;

    /**
     * View model constructor
     * @param application of type {@link Application}
     */
    public ContentViewModel (Application application){
        super(application);

        //Ijects RepositoryModule
        ApplicationSingleton.getInstance().getBaseComponents().inject(this);

        init();

    }

    /**
     * Initialization i.e. primary works
     */
    private void init(){
       // repositoryModule.invokeApiContentList();
    }


}
