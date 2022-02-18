package com.example.mymovies.mvvm.model.network

import com.example.mymovies.mvvm.model.entity.modelclass.MainMovieResponseModel
import com.example.mymovies.mvvm.model.entity.modelclass.MovieDetailResponseModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieApiService {

    @GET(".")
    fun getMovies( @Query("s") movieName: String,
                   @Query("page") pageNo: Int): Call<MainMovieResponseModel>

    @GET(".")
    fun getMovieDetail(@Query("i") movieId: String?): Call<MovieDetailResponseModel>
}