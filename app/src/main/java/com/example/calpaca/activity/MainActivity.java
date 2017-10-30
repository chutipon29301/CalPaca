package com.example.calpaca.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.calpaca.R;
import com.example.calpaca.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentContainer, MainFragment.newInstance(), "MainFragment")
                .commit();
    }
}
