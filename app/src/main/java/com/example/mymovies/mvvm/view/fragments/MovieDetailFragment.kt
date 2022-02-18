package com.example.mymovies.mvvm.view.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.example.mymovies.R
import com.example.mymovies.databinding.MovieDetailFragmentBinding
import com.example.mymovies.mvvm.viewmodel.CommonViewModel
import com.example.mymovies.mvvm.model.entity.modelclass.MovieDetailResponseModel as MovieDetailResponseModel1

class MovieDetailFragment : BaseFragment() {

    private var movieID: String? = ""
    private lateinit var movieDetailFragmentBinding: MovieDetailFragmentBinding
    private lateinit var viewModel: CommonViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieDetailFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.movie_detail_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(CommonViewModel::class.java)


        // Set the viewmodel for databinding - this allows the bound layout access
        // to all the data in the VieWModel
        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        movieDetailFragmentBinding.lifecycleOwner = viewLifecycleOwner


        return movieDetailFragmentBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieID = arguments?.getString("movieID")
        viewModel.getMoviesDetails(movieID)!!.observe(viewLifecycleOwner, Observer {
            updateDetailsData(it)
        })
    }

    private fun updateDetailsData(imh: MovieDetailResponseModel1) {
        movieDetailFragmentBinding.viewModel = imh
    }

}
