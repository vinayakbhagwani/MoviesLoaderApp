package com.example.mymovies.mvvm.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentMovieListBinding
import com.example.mymovies.mvvm.model.entity.modelclass.Movie
import com.example.mymovies.mvvm.view.actvities.BaseActivity
import com.example.mymovies.mvvm.view.actvities.MainActivity
import com.example.mymovies.mvvm.view.adapters.MovieItemClickListener
import com.example.mymovies.mvvm.view.adapters.MovieListAdapter
import com.example.mymovies.mvvm.viewmodel.CommonViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.progress_loader_layout.*

class MovieListFragment : BaseFragment(), MovieItemClickListener {

    private lateinit var viewModel: CommonViewModel
    private lateinit var mFragmentMovieListBinding: FragmentMovieListBinding
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mMovieListAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFragmentMovieListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        return mFragmentMovieListBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
        setupViewModel()
    }

    //method to get the result
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(CommonViewModel::class.java)
        viewModel.resetPageNumber()
        viewModel.getMovies("Superman", viewModel.pageNo)!!.observe(viewLifecycleOwner, Observer {
            showLoader(false)
            mMovieListAdapter.setList(it as ArrayList<Movie>)
        })
    }

    private fun showLoader(loading: Boolean) {
        progressBarr.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun setupUI() {
        showLoader(true)
        mMovieListAdapter = MovieListAdapter(context, null, this)
        mRecyclerView = mFragmentMovieListBinding.recyclerview
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(context))
        mRecyclerView.setAdapter(mMovieListAdapter)
        mRecyclerView.addOnScrollListener(scrollListener)

    }

    //scroll to get Next Page of result
    private val scrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    showLoader(true)
                    viewModel.getNextData("Superman")
                }
            }
        }

    /*method for handling item click of recycler view*/
    override fun onMovieItemClick(view: View, movie: Movie) {
        when (view.id) {
            R.id.llParent -> {
                showDetailsFragment(movie.imdbID,movie.title)
            }
        }
    }

    fun showDetailsFragment(moviewID: String?,movieName: String?) {
        val movieDetailFragment = MovieDetailFragment()
        (activity as MainActivity).setToolBar(movieName)
        val bundle = Bundle()
        bundle.putString("movieID", moviewID)
        movieDetailFragment.arguments = bundle
        (activity as BaseActivity).launchFragment(movieDetailFragment, true)
    }
}