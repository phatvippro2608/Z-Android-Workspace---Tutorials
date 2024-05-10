package com.ntp.apiwithretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ntp.apiwithretrofit.R;
import com.ntp.apiwithretrofit.databinding.ItemRowBinding;
import com.ntp.apiwithretrofit.respone.Movie;
import com.ntp.apiwithretrofit.respone.MovieList;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    List<Movie> items;
    Context context;
    ItemRowBinding binding;
    static final String POSTER_BASEURL = "https://image.tmdb.org/t/p/w342";

    public MovieAdapter(List<Movie> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        Movie item = items.get(position);

            binding.tvMovieName.setText(item.getTitle());

        binding.tvRate.setText(item.getVote_average()+"");
        String movieImageURL = POSTER_BASEURL + item.getPoster_path();
        Glide.with(context)
                .load(movieImageURL)
                .into(binding.imgMovie)
//                .onLoadCleared(context.getDrawable(R.drawable.poster_placeholder));

                .waitForLayout();
        binding.tvLanguage.setText(item.getOriginal_language());
        binding.tvDate.setText(item.getRelease_date());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(ItemRowBinding binding) {
            super(binding.getRoot());
        }
    }
}
