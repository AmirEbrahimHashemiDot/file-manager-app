package com.lucifer.filemanager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileFragment extends Fragment {

    RecyclerView rvFileFragment;
    String path;
    FileAdapter fileAdapter;
    EditText editTextText;
    Button btnAddFolder, btnDelete, btnCopy, btnMove;

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
        editTextText = view.findViewById(R.id.editTextText);
        btnAddFolder = view.findViewById(R.id.btn_add_folder);
        btnDelete = view.findViewById(R.id.btn_delete);
        btnCopy = view.findViewById(R.id.btn_copy);
        btnMove = view.findViewById(R.id.btn_move);

        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File source = new File(path + File.separator + "newFile.txt");
                File destination = new File(path + File.separator + "ss" + File.separator + "newFile2.txt");

                try {
                    move(source, destination);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File source = new File(path + File.separator + "file.txt");
                File destination = new File(path + File.separator + "test2" + File.separator + "file.txt");
                try {
                    copyFolder(source, destination);
                    Toast.makeText(getContext(), "File Copied!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = editTextText.getText().toString();
                File file = new File(path + File.separator + fileName);
                deleteFolder(file);
                editTextText.setText("");
            }
        });

        btnAddFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFolder(editTextText.getText().toString());
                editTextText.setText("");
            }
        });

        rvFileFragment.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));

        File file = new File(path);
        File[] files = file.listFiles();
        fileAdapter = new FileAdapter(Arrays.asList(files), new FileAdapter.OnFileItemClick() {
            @Override
            public void onItemCLick(File file) {
                ((MainActivity) getActivity()).listFile(file.getPath(), true);
            }
        });
        rvFileFragment.setAdapter(fileAdapter);
        return view;

    }

    public void createFolder(String fileName) {
        File file = new File(path + File.separator + fileName);
        if (!file.exists()) {
            if (file.mkdir()) {
                fileAdapter.addFile(file);
            }
        }
    }

    public void deleteFolder(File file) {
        if (file.delete()) {
            fileAdapter.deleteFile(file);
        }
    }

    public void copyFolder(File source, File destination) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(source);
        FileOutputStream fileOutputStream = new FileOutputStream(destination);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = fileInputStream.read()) > 0) {
            fileOutputStream.write(buffer, 0, length);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    public void move(File source, File destination) throws IOException {
        //copy
        copyFolder(source, destination);

        //delete
        deleteFolder(source);
    }
}