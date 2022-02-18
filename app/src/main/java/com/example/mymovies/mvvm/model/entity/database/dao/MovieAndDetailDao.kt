package com.android.myapplication.movies.persistence.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.mymovies.mvvm.model.entity.modelclass.Movie

@Dao
interface MovieAndDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movies: List<Movie>)

    @Query("SELECT * from movie WHERE page = :pageNo")
    fun getMovies(pageNo: Int): List<Movie>
}