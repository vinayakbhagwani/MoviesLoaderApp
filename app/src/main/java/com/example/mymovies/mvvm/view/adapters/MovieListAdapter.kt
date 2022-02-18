package com.example.mymovies.mvvm.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovies.R
import com.example.mymovies.databinding.MovieListItemBinding
import com.example.mymovies.mvvm.model.entity.modelclass.Movie

class MovieListAdapter(internal var context: Context?,
                       private var mMovieList: ArrayList<Movie>?,
                       private val listener: MovieItemClickListener):
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
        DataBindingUtil.inflate<MovieListItemBinding>(LayoutInflater.from(parent.context),
            R.layout.movie_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieListItemBinding.movie = mMovieList?.get(position)!!
        holder.movieListItemBinding.llParent.setOnClickListener {
            listener.onMovieItemClick(holder.movieListItemBinding.llParent, mMovieList?.get(position)!!)
        }

    }

    override fun getItemCount(): Int {
        if(mMovieList != null)
            return mMovieList!!.size
       return  0
    }

    inner class ViewHolder(
        val movieListItemBinding: MovieListItemBinding
    ) : RecyclerView.ViewHolder(movieListItemBinding.root) {
    }

     fun setList(list : ArrayList<Movie>){
         if(mMovieList==null)
          mMovieList = list
         else if (mMovieList!=null && mMovieList!!.size>0)
             // add new list item in old list
             mMovieList!!.addAll(list)

        this.notifyDataSetChanged()
    }
}