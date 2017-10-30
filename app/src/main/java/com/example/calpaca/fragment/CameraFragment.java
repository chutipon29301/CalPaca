package com.example.calpaca.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.calpaca.R;

/**
 * Created by admin on 30/10/2017 AD.
 */

public class CameraFragment extends Fragment {

    private static CameraFragment instance;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private Button btnTakePhoto;
    private ImageView mImageView;

    public static CameraFragment newInstance() {
        if (instance == null) {
            instance = new CameraFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_camera, container, false);
        initInstance(rootView, savedInstanceState);
        return rootView;
    }

    private void initInstance(View rootView, Bundle savedInstanceState) {
        btnTakePhoto = (Button) rootView.findViewById(R.id.btnTakePhoto);
        btnTakePhoto.setOnClickListener(takePhotoListener);

        mImageView = (ImageView) rootView.findViewById(R.id.mImageView);
    }

    View.OnClickListener takePhotoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnTakePhoto) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, 1);
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("CameraDebug", "resultCode: " + resultCode);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == -1) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }
}
