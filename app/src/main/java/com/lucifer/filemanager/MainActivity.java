package com.lucifer.filemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import java.io.File;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameFileMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameFileMain = findViewById(R.id.frameFileMain);
        File file = getExternalFilesDir(null);
        assert file != null;
        listFile(file.getPath(), false);

    }

    public void listFile(String path, boolean addToBackStack) {
        Bundle bundle = new Bundle();
        bundle.putString("path", path);
        FileFragment fileFragment = new FileFragment();
        fileFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.frameFileMain, fileFragment);
        transaction.commit();
    }
}