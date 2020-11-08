package com.braula.abaxapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.braula.abaxapp.R
import com.braula.abaxapp.model.Beer
import com.braula.abaxapp.model.ApiError
import com.braula.abaxapp.view.adapter.BeerAdapter
import com.braula.abaxapp.viewmodel.BeerViewModel
import kotlinx.android.synthetic.main.fragment_beer_list.*
import org.koin.android.ext.android.inject

class BeerListFragment: Fragment() {
    companion object {
        fun newInstance(): BeerListFragment {
            return BeerListFragment()
        }
    }

    private lateinit var callback: OnBeerSelectedListener
    private val model: BeerViewModel by inject()

    fun setOnBeerSelectedListener(callback: OnBeerSelectedListener) {
        this.callback = callback
    }

    interface OnBeerSelectedListener {
        fun onBeerSelected(position: Int)
    }

    private lateinit var beerAdapter: BeerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beer_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListView()
        setupObservers()

        loadData()
    }

    private fun setupListView() {
        beerAdapter = BeerAdapter(requireActivity())
        listView.adapter = beerAdapter
        listView.setOnItemClickListener { _, _, position, _ ->
            callback.onBeerSelected(position)
        }
    }

    private fun loadData() {
        if (!model.beersLoaded) {
            showLoading()
            model.loadBeers()
        }
    }

    private fun showLoading() {
        loadingView.visibility = View.VISIBLE
    }

    private fun setupObservers() {
        model.beers.observe(requireActivity(), Observer {
            handleData(it)
            hideLoading()
        })

        model.error.observe(requireActivity(), Observer {
            handleError(it)
            hideLoading()
        })
    }

    private fun handleData(beers: ArrayList<Beer>) {
        beerAdapter.items = beers
        beerAdapter.notifyDataSetChanged()
    }

    private fun handleError(error: ApiError) {
        if (error == ApiError.LOADING_ERROR) {
            Toast.makeText(requireContext(), R.string.loading_error, Toast.LENGTH_LONG).show()
        }
    }

    private fun hideLoading() {
        loadingView.visibility = View.GONE
    }
}