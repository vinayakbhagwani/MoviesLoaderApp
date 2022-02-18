package com.example.mymovies.mvvm.model.respository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.myapplication.movies.persistence.MovieDB
import com.android.myapplication.movies.persistence.dao.MovieAndDetailDao
import com.example.mymovies.mvvm.model.entity.modelclass.MainMovieResponseModel
import com.example.mymovies.mvvm.model.entity.modelclass.Movie
import com.example.mymovies.mvvm.model.entity.modelclass.MovieDetailResponseModel
import com.example.mymovies.mvvm.model.network.RetrofitBuilder
import com.example.mymovies.mvvm.view.utils.Utility
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Array.get
import kotlin.coroutines.CoroutineContext

class MainRespository(application: Application) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var movieDao: MovieAndDetailDao?

    val mainMovieResponseModel = MutableLiveData<List<Movie>>()
    val moviedetails = MutableLiveData<MovieDetailResponseModel>()
    private val appContext = application

    init {
        val db = MovieDB.getDatabase(appContext)
        movieDao = db?.messageDao()
    }

    fun getMovies(moviename: String, pageNo: Int): MutableLiveData<List<Movie>> {

        if (Utility.getInstance()?.isOnline(appContext)!!) {
//            fetch from server
            val call = RetrofitBuilder.apiService.getMovies(moviename,pageNo)
            call.enqueue(object : Callback<MainMovieResponseModel> {
                override fun onResponse(
                    call: Call<MainMovieResponseModel>,
                    response: Response<MainMovieResponseModel>
                ) {
                    if (response.isSuccessful) {
                        if (response.code() == 200) {
                            saveCallResult(response.body(),pageNo)
                            mainMovieResponseModel.value = response.body()?.movieList
                        }
                    } else {
                        Log.v("ERROR : ", "ERROR")
                    }

                }

                override fun onFailure(call: Call<MainMovieResponseModel>, t: Throwable) {
                    // TODO("Not yet implemented")
                    Log.v("DEBUG : ", t.message.toString())
                }
            })
        } else {
            // fetch from DB
            launch {
                mainMovieResponseModel.value = getMessageBG(pageNo)
            }
        }

        return mainMovieResponseModel
    }

    fun saveCallResult(item: MainMovieResponseModel?,pageNo :Int) {
        launch { setMessageBG(item,pageNo) }
    }

    private suspend fun setMessageBG(item: MainMovieResponseModel?,pageNo :Int) {
        withContext(Dispatchers.IO) {
            item?.let {
                val list: ArrayList<Movie>? = (item.movieList)?.let { ArrayList(it) }
                val newList: ArrayList<Movie>? = ArrayList<Movie>()
                list?.forEach {
                    val movie = it
                    movie.page  = pageNo
                    newList?.add(movie)
                }
                newList?.let {
                    movieDao?.insertMovieList(newList)
                }
            }
        }
    }


    private suspend fun getMessageBG(pageNo:Int): List<Movie>? {
        return withContext(Dispatchers.IO) {
          return@withContext movieDao?.getMovies(pageNo)
        }
    }



    fun getMoviesDetails(id: String?): MutableLiveData<MovieDetailResponseModel> {
        val call = RetrofitBuilder.apiService.getMovieDetail(id)

        call.enqueue(object : Callback<MovieDetailResponseModel> {

            override fun onResponse(
                call: Call<MovieDetailResponseModel>,
                response: Response<MovieDetailResponseModel>
            ) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", response.body().toString())

                /**/
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        moviedetails.value = response.body()
                    }
                } else {
                    Log.v("ERROR : ", "ERROR")
                }

            }

            override fun onFailure(call: Call<MovieDetailResponseModel>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", t.message.toString())
            }
        })

        return moviedetails
    }
}