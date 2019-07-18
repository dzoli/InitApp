package com.example.novicabjelica.initapp.di;

import android.content.Context;

import com.example.novicabjelica.initapp.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Context application);

        AppComponent build();
    }

    void inject(App target);
}
