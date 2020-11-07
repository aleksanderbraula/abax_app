package com.braula.abaxapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.braula.abaxapp.R

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beer_details, container, false)
    }

}