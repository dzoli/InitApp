package com.example.novicabjelica.initapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View {
    Button fab;
    TextView textView;

    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        textView = findViewById(R.id.textView);

        fab.setOnClickListener(view -> {
            presenter.createTopic(this);

        });
    }

    @Override
    public void showClickText() {
        textView.setText("Fab was clicked!");
    }
}
