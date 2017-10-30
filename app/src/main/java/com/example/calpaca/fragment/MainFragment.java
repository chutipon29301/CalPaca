package com.example.calpaca.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.calpaca.R;

/**
 * Created by admin on 30/10/2017 AD.
 */

public class MainFragment extends Fragment {
    private static MainFragment instance;

    private Button button;

    public static MainFragment newInstance() {
        if (instance == null) {
            instance = new MainFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment(savedInstanceState);
    }

    private void initFragment(Bundle savedInstanceState) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstance(rootView, savedInstanceState);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initInstance(final View rootView, Bundle savedInstanceState) {
        button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Button Click", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
