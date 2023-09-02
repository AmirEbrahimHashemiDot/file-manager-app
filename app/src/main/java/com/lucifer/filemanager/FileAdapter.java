package com.lucifer.filemanager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {

    List<File> fileList;

    public FileAdapter (List<File> fileList) {
        this.fileList = fileList;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class FileViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFileItemIcon;
        TextView tvFileItemName;
        ConstraintLayout fileItemParent;

        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFileItemIcon = itemView.findViewById(R.id.imgFileItemIcon);
            tvFileItemName = itemView.findViewById(R.id.tvFileItemName);
            fileItemParent = itemView.findViewById(R.id.fileItemParent);
        }
    }
}