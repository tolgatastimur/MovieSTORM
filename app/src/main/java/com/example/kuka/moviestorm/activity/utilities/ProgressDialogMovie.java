package com.example.kuka.moviestorm.activity.utilities;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.kuka.moviestorm.R;
import com.example.kuka.moviestorm.activity.activity.MainActivity;


public class ProgressDialogMovie extends android.app.ProgressDialog
{
    public static ProgressDialogMovie builder()
    {
        ProgressDialogMovie dialogCoffee = new ProgressDialogMovie();

        dialogCoffee.setIndeterminate(true);
        dialogCoffee.setCancelable(false);
        return dialogCoffee;
    }

    public ProgressDialogMovie()
    {
        super(MainActivity.activity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress_movie);

        ImageView image = (ImageView) findViewById(R.id.progressIcon);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);



    }
}
