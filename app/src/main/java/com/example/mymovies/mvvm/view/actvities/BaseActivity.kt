package com.example.mymovies.mvvm.view.actvities

import androidx.appcompat.app.AppCompatActivity
import com.example.mymovies.R
import com.example.mymovies.mvvm.view.fragments.BaseFragment

open class BaseActivity : AppCompatActivity() {

    /* method for adding and replacing the fragment*/
    fun launchFragment(fragment: BaseFragment, addtoBackstack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        if (addtoBackstack) {
            transaction.replace(R.id.fragment_container, fragment)
                .addToBackStack(fragment.javaClass.simpleName)
                .commitAllowingStateLoss()
        } else {
            transaction.replace(R.id.fragment_container, fragment)
                .commitAllowingStateLoss()
        }
    }
}