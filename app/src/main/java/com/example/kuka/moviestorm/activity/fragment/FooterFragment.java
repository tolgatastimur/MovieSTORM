package com.example.kuka.moviestorm.activity.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FooterFragment extends Fragment {
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_footer, container, false);
        ButterKnife.bind(this, view);
        fragmentManager = MainActivity.activity.getSupportFragmentManager();
        navigationBar();

        return view;
    }


    public void navigationBar() {
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.onTheater:
                        MainActivity.activity.setNewFragment(new VizyonFragment(), R.id.contentFL, "", false, true, false, false);
                        break;
                    case R.id.movieNews:
                        MainActivity.activity.setNewFragment(new PopularMovieFragment(), R.id.contentFL, "", false, true, false, false);
                        break;
                    case R.id.bestMovies:
                        MainActivity.activity.setNewFragment(new MovieFragment(), R.id.contentFL, "movieFragment", true, true, false, false);
                        break;
                    case R.id.watchList:
                        MainActivity.activity.setNewFragment(new WatchListFragment(), R.id.contentFL, "", false, false, false, false);
                        break;
                    case R.id.search:
                        MainActivity.activity.setNewFragment(new SearchFragment(), R.id.contentFL, "", true, true, false, false);
                        break;
                }
                return true;
            }
        });
    }
}
