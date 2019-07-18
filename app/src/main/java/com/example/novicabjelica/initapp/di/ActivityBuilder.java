package com.example.novicabjelica.initapp.di;

import com.example.novicabjelica.initapp.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity contributeMainActivity();

}
