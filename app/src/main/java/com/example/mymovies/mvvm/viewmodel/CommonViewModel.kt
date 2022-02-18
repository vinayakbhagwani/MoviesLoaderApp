package com.example.mymovies.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovies.mvvm.model.entity.modelclass.MainMovieResponseModel
import com.example.mymovies.mvvm.model.entity.modelclass.Movie
import com.example.mymovies.mvvm.model.entity.modelclass.MovieDetailResponseModel
import com.example.mymovies.mvvm.model.respository.MainRespository
import com.example.mymovies.mvvm.view.utils.Utility

class CommonViewModel(application: Application) : AndroidViewModel(application) {
    var servicesLiveData: MutableLiveData<List<Movie>>? = null
    var MovieLiveData: MutableLiveData<MovieDetailResponseModel>? = null
    private var repository:MainRespository = MainRespository(application)
    var pageNo : Int = 1

    fun getMovies(movieName: String, pageNo : Int) : MutableLiveData<List<Movie>>? {
       servicesLiveData = repository.getMovies(movieName,pageNo)
        this.pageNo = pageNo
        return servicesLiveData
    }

    fun resetPageNumber() {
        pageNo = 1
    }

    fun getMoviesDetails(id : String?) : MutableLiveData<MovieDetailResponseModel>? {
        MovieLiveData = repository.getMoviesDetails(id)
        return MovieLiveData
    }

    fun getNextData(movieName: String) :MutableLiveData<List<Movie>>?  {
        this.pageNo = this.pageNo +1
        servicesLiveData = repository.getMovies(movieName,pageNo)
        return servicesLiveData
    }
}