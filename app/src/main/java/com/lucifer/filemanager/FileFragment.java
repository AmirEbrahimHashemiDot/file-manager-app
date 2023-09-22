package com.lucifer.filemanager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.Arrays;

public class FileFragment extends Fragment {

    RecyclerView rvFileFragment;
    String path;
    FileAdapter fileAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        path = bundle.getString("path");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_file, container, false);
        rvFileFragment = view.findViewById(R.id.rvFileFragment);
        rvFileFragment.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));

        File file = new File(path);
        File[] files = file.listFiles();
        fileAdapter = new FileAdapter(Arrays.asList(files));
        rvFileFragment.setAdapter(fileAdapter);
        return view;

    }
}