package com.example.kuka.moviestorm.activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.activity.MainActivity;
import com.example.kuka.moviestorm.activity.fragment.MovieCategoriesDetailFragment;
import com.example.kuka.moviestorm.activity.model.Genre;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private ArrayList<Genre> genres;

    public Integer[] imagePath = {
            R.drawable.action_image,
            R.drawable.adventura_image,
            R.drawable.animation_image,
            R.drawable.comedy_poster,
            R.drawable.crime_image,
            R.drawable.documentary_image,
            R.drawable.drama_image,
            R.drawable.family_image,
            R.drawable.fantasy_image,
            R.drawable.history_image,
            R.drawable.scary_image,
            R.drawable.music_image,
            R.drawable.mistery_image,
            R.drawable.romance_image,
            R.drawable.sci_fi_image,
            R.drawable.tv_movies_image,
            R.drawable.thriller_image,
            R.drawable.war_image,
            R.drawable.western_image,
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
            Picasso.get()
                    .load(imagePath[position])
                    .fit()
                    .into(holder.movieCategoryImage);
        }

        holder.movieCategoryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.activity.setNewFragment(MovieCategoriesDetailFragment.newInstance(article), R.id.mainFrame, "", true, false, false, false);

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