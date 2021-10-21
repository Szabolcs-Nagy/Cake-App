package com.cake.cakeapp.modules.cakelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cake.cakeapp.MainActivity
import com.cake.cakeapp.R
import com.cake.cakeapp.modules.cakedetails.CakeDetailsFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.cake_list_fragment.*
import kotlinx.coroutines.flow.collect


/**
 * [Fragment] subclass
 * The [CakeListFragment.newInstance] factory method
 * creates an instance of this fragment
 */

@AndroidEntryPoint
class CakeListFragment : Fragment() {

    private val cakeListViewModel: CakeListViewModel by viewModels()

    private lateinit var cakesRecycleViewAdapter: CakesRecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cake_list_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cakesRecycleViewAdapter = CakesRecycleViewAdapter(requireContext()){
            val cakeDetailsFragment = CakeDetailsFragment.newInstance(it)
            (activity as MainActivity).replaceFragment(cakeDetailsFragment,CakeDetailsFragment.TAG)
        }
        recyclerView.apply {
        layoutManager = LinearLayoutManager(requireContext())
            adapter = cakesRecycleViewAdapter
        }
        bindViewModel()
        try{
        cakeListViewModel.fetchCakes()

        }catch (exception: Exception){
            Toast.makeText(activity, "Error connecting to the network", Toast.LENGTH_SHORT).show()
        }

    }

    /**
     * Binding the View Model
     * Here we verify which CakeEvent is true
     * Is the event successful?
     * Is the event loading?
     * Did we get an error while trying to reach the network?
     * Is the event empty?
     */
    private  fun bindViewModel() {

        lifecycleScope.launchWhenStarted {
            cakeListViewModel.cakes.collect { event ->
                when (event) {
                    is CakeEvent.Success -> {
                       progressBar.visibility = View.INVISIBLE
                        updateUI(event.cakes)
                    }
                    is CakeEvent.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is CakeEvent.Error -> {
                        progressBar.visibility = View.GONE
                        Snackbar.make(recyclerView, "Failed to get response from network", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                    is CakeEvent.Empty -> {
                       progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun updateUI(cakes: List<Cake>) {
        cakesRecycleViewAdapter.setData(cakes)
    }
    companion object {

        val TAG = CakeListFragment::class.java.simpleName

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CakeListFragment.
         */
        @JvmStatic
        fun newInstance() = CakeListFragment()
    }
}