package com.example.kuka.moviestorm.activity.utilities;

/**
 * Created by ender on 10/10/2017.
 */

public class ProgressDialogMovieHelper
{
    public static ProgressDialogMovie progressDialogMovie;

    public static void showCircularProgressDialogMovie()
    {
        if (progressDialogMovie != null && progressDialogMovie.isShowing())
        {
            return;
        }
        progressDialogMovie = ProgressDialogMovie.builder();
        progressDialogMovie.show();
    }

    public static void dismiss()
    {
        if (progressDialogMovie != null && progressDialogMovie.isShowing())
        {
            progressDialogMovie.dismiss();
            progressDialogMovie = null;
        }
    }
}
