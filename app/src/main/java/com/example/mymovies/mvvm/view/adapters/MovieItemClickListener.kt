package com.example.mymovies.mvvm.view.adapters

import android.view.View
import com.example.mymovies.mvvm.model.entity.modelclass.Movie

interface MovieItemClickListener {
    /* callback method for handling list item click*/
    fun onMovieItemClick(view: View, movie: Movie)
}