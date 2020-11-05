package com.braula.abaxapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.braula.abaxapp.R
import com.braula.abaxapp.model.Beer
import com.braula.abaxapp.view.adapter.BeerAdapter
import kotlinx.android.synthetic.main.fragment_beer_list.*

class BeerListFragment: Fragment() {
    private lateinit var callback: OnBeerSelectedListener

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

        beerAdapter = BeerAdapter(activity!!)
        beerAdapter.items = arrayListOf(Beer("1", "beer1", "", "4"))

        listView.adapter = beerAdapter
        listView.setOnItemClickListener { _, _, position, _ ->
            callback.onBeerSelected(position)
        }

    }
}