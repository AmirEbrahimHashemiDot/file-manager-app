package com.lucifer.filemanager;

import android.view.LayoutInflater;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_item, parent, false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        File file = fileList.get(position);
        if (file.isDirectory()) {
            holder.imgFileItemIcon.setImageResource(R.drawable.baseline_folder_24);
        } else {
            holder.imgFileItemIcon.setImageResource(R.drawable.baseline_insert_drive_file_24);
        }
        holder.tvFileItemName.setText(file.getName());

    }

    @Override
    public int getItemCount() {
        return fileList.size();
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