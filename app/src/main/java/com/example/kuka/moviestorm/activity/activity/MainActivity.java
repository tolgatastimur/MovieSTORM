package com.example.kuka.moviestorm.activity.activity;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.fragment.FooterFragment;
import com.example.kuka.moviestorm.activity.fragment.MovieFragment;
import com.example.kuka.moviestorm.activity.fragment.VizyonFragment;

public class MainActivity extends AppCompatActivity {
    public static MainActivity activity;
    public static LayoutInflater inflater;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        fragmentManager = getSupportFragmentManager();

        prepareTransactions();
    }

    private void prepareTransactions() {

        setNewFragment(new VizyonFragment(), R.id.contentFL, null, true, true, false, false);
        setNewFragment(new FooterFragment(), R.id.footerContainer, null, false, true, false, false);
        fragmentManager.executePendingTransactions();

    }

    @SuppressLint("CommitTransaction")
    public void setNewFragment(Fragment fragment, int containerId, String tag, boolean shouldAddBackStack, boolean shouldReplace, boolean shouldAnimation, boolean shouldTransition) {


        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if (shouldReplace) {
            fragmentTransaction.replace(containerId, fragment);
        } else {
            fragmentTransaction.add(containerId, fragment);
        }
        if (shouldAnimation) {
            //fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        }
        if (shouldAddBackStack) {
            fragmentTransaction.addToBackStack(tag);
        }
        if (shouldTransition) {
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
        fragmentTransaction.commitAllowingStateLoss();

    }


}
