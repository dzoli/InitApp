package com.example.novicabjelica.initapp;

public class MainPresenterImpl implements MainContract.Presenter {

    @Override
    public void createTopic(MainContract.View view) {
        view.showClickText();
    }
}
