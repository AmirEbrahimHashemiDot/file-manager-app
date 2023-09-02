package com.lucifer.filemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;
import android.widget.FrameLayout;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    File file;
    FileAdapter fileAdapter;
    FrameLayout frameFileMain;
    FileFragment fileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setUpViews();

        file = getExternalFilesDir(null);
        File[] files = file.listFiles();
        fileAdapter = new FileAdapter(Arrays.asList(files));
    }

    private void setUpViews() {
        frameFileMain = findViewById(R.id.frameFileMain);
        fileFragment = new FileFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameFileMain, fileFragment);
        transaction.commit();

    }
}