package com.example.mymovies.mvvm.view.actvities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.mymovies.R
import com.example.mymovies.databinding.ActivityMainBinding
import com.example.mymovies.mvvm.view.fragments.MovieListFragment

class MainActivity : BaseActivity() {
    private lateinit var mActivityBinding: ActivityMainBinding
    private  var listFragment = MovieListFragment()
    private lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        toolbar = mActivityBinding.toolbar
        setSupportActionBar(toolbar)

        if (savedInstanceState == null){
            launchFragment(listFragment,false)
        }
    }

     fun setToolBar(movieName :String?){
        toolbar.title  = movieName
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1){
            toolbar.title = resources.getString(R.string.app_name)
            super.onBackPressed()
        }else{
            super.onBackPressed()
        }
    }

}
