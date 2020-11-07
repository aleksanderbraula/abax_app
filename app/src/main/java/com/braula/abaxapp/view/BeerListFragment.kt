package com.braula.abaxapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.braula.abaxapp.R
import com.braula.abaxapp.model.Beer
import com.braula.abaxapp.view.adapter.BeerAdapter
import com.braula.abaxapp.viewmodel.BeerViewModel
import kotlinx.android.synthetic.main.fragment_beer_list.*

class BeerListFragment: Fragment() {
    companion object {
        fun newInstance(): BeerListFragment {
            return BeerListFragment()
        }
    }

    private lateinit var callback: OnBeerSelectedListener
    private val model: BeerViewModel by viewModels()

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

        beerAdapter = BeerAdapter(requireActivity())
        model.loadBeers()

        listView.adapter = beerAdapter
        listView.setOnItemClickListener { _, _, position, _ ->
            callback.onBeerSelected(position)
        }

        model.beers.observe(requireActivity(), Observer {
            handleData(it)
        })
    }

    private fun handleData(beers: ArrayList<Beer>) {
        beerAdapter.items = beers
        beerAdapter.notifyDataSetChanged()
    }
}