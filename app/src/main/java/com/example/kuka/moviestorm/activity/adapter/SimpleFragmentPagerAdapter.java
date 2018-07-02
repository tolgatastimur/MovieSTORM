package com.example.kuka.moviestorm.activity.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.fragment.MovieCategoriesFragment;
import com.example.kuka.moviestorm.activity.fragment.NextComingFragment;
import com.example.kuka.moviestorm.activity.fragment.TopMovieFragment;
import com.example.kuka.moviestorm.activity.fragment.VizyonFragment;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    //buna switch case atarsak tıklama işlemi gerçekleşecek
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {

            return new TopMovieFragment();
        } else if (position == 1) {
            return new MovieCategoriesFragment();
        } else if (position == 2) {
            return new NextComingFragment();
        } else {
            return new TopMovieFragment();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.top_movies);
            case 1:
                return mContext.getString(R.string.header_categories);
            case 2:
                return mContext.getString(R.string.next_program);
            default:
                return null;
        }
    }

}