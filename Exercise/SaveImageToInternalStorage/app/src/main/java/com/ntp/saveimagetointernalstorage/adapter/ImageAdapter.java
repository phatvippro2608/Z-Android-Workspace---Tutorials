package com.ntp.saveimagetointernalstorage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ntp.saveimagetointernalstorage.databinding.ViewholderImageListBinding;
import com.ntp.saveimagetointernalstorage.domain.Picture;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    ArrayList<Picture> items;
    Context context;

    ViewholderImageListBinding binding;

    public ImageAdapter(ArrayList<Picture> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderImageListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load("file://"+items.get(position).getName())
                .into(binding.imgPic);
        binding.txtTilte.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull ViewholderImageListBinding binding) {
            super(binding.getRoot());
        }
    }
}
