package com.braula.abaxapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.braula.abaxapp.R
import com.braula.abaxapp.model.Beer
import com.braula.abaxapp.viewmodel.BeerViewModel

class BeerDetailsFragment: Fragment() {
    companion object {
        private const val POSITION_KEY = "position_key"

        fun newInstance(position: Int): BeerDetailsFragment {
            val fragment = BeerDetailsFragment()

            val b = Bundle()
            b.putInt(POSITION_KEY, position)
            fragment.arguments = b

            return fragment
        }
    }

    private val model: BeerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beer_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = requireArguments().getInt(POSITION_KEY)

        model.beers.value?.let{
            val beer = it[position]
            showBeerDetails(beer)
        }
    }

    private fun showBeerDetails(beer: Beer) {
        println(beer)
    }
}