package com.example.mymovies.mvvm.view.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("glideImg")
fun loadImage(view: ImageView, url: String) {
    if(url!=null)
    Glide.with(view)
        .load(url)
        .into(view)
}