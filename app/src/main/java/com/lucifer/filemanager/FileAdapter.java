package com.lucifer.filemanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {

    List<File> fileList;

    OnFileItemClick onFileItemClick;

    public FileAdapter (List<File> fileList, OnFileItemClick onFileItemClick) {
        this.fileList = new ArrayList<>(fileList);
        this.onFileItemClick = onFileItemClick;
    }

    public void deleteFile(File file) {
        int index = fileList.indexOf(file);
        if (index > -1) {
            fileList.remove(index);
            notifyItemRemoved(index);
        }
    }

    public void addFile(File file) {
        fileList.add(0, file);
        notifyItemInserted(0);
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (file.isDirectory()) {
                    onFileItemClick.onItemCLick(fileList.get(holder.getAdapterPosition()));
                } else if (file.isFile()) {
                    Toast.makeText(holder.imgFileItemIcon.getContext(), "Can't Open Files.", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

    public interface OnFileItemClick {
        void onItemCLick(File file);
    }
}