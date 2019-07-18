package com.example.novicabjelica.initapp.di;

import com.example.novicabjelica.initapp.MainContract;
import com.example.novicabjelica.initapp.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
class MainActivityModule {

    @Provides
    MainContract.Presenter providePresenter() {
        return new MainPresenterImpl();
    }

}

