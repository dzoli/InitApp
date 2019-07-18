package com.example.novicabjelica.initapp;

import android.app.Activity;
import android.app.Application;

import com.example.novicabjelica.initapp.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().application(this).build().inject(this);
    }

    /**
     * Returns an {@link AndroidInjector} of {@link Activity}s.
     */
    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }


    public void setDispatchingActivityInjector(DispatchingAndroidInjector<Activity> dispatchingAndroidInjector) {
        this.dispatchingAndroidInjector = dispatchingAndroidInjector;
    }

}
