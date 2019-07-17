package com.example.novicabjelica.initapp;

public interface MainContract {

    interface View {
        void showClickText();
    }

    interface Presenter {
        void createTopic(View view);
    }
}
