package com.example.kuka.moviestorm.activity.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.activity.MainActivity;
import com.example.kuka.moviestorm.activity.fragment.MovieCategoriesDetailFragment;
import com.example.kuka.moviestorm.activity.fragment.MovieDetailFragment;
import com.example.kuka.moviestorm.activity.model.Genre;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private ArrayList<Genre> genres;

    public Integer[] imagePath = {
            R.drawable.adventure_image,
            R.drawable.action_image
    };


    public CategoriesAdapter(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_categories, parent, false);
        return new CategoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriesAdapter.ViewHolder holder, int position) {
        final Genre article = genres.get(position);
        holder.movieCategoryText.setText((CharSequence) article.name);

        if (position < imagePath.length) {
            // holder.movieCategoryImage.setImageResource(imagePath[position]);
            Picasso.get()
                    .load(imagePath[position])
                    .fit()
                    .into(holder.movieCategoryImage);
        }

        holder.movieCategoryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.activity.setNewFragment(MovieCategoriesDetailFragment.newInstance(article), R.id.mainFrame, "", true, true, false, false);

            }
        });
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView movieCategoryText;
        @BindView(R.id.movieCategoryImage)
        ImageView movieCategoryImage;

        public ViewHolder(View itemView) {
            super(itemView);
            movieCategoryText = (TextView) itemView.findViewById(R.id.movieCategoryText);
            ButterKnife.bind(this, itemView);

        }
    }
}