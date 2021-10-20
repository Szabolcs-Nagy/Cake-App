package com.cake.cakeapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.NonNull
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.cake.cakeapp.modules.cakelist.CakeListFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cake.cakeapp.modules.cakelist.CakeListViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var viewModel: CakeListViewModel
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(CakeListViewModel::class.java)
        // Getting reference of swipeRefreshLayout and recyclerView
        swipeLayout = findViewById<SwipeRefreshLayout>(R.id.mSwipeRefreshLayout)
        swipeLayout.setOnRefreshListener {
            viewModel.refreshData()
            swipeLayout.isRefreshing = false
        }
        replaceFragment(CakeListFragment.newInstance(), CakeListFragment.TAG)
        // calling the action bar
        val actionBar: ActionBar? = supportActionBar
        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    // function to the button on press that enables to go back
    override fun onOptionsItemSelected(@NonNull item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, tag)
            .addToBackStack("").commit()
    }
}