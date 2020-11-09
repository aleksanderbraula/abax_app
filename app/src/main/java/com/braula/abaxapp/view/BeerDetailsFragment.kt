package com.braula.abaxapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.braula.abaxapp.R
import com.braula.abaxapp.model.Beer
import com.braula.abaxapp.model.Ingredient
import com.braula.abaxapp.model.Method
import com.braula.abaxapp.view.adapter.IngredientAdapter
import com.braula.abaxapp.view.adapter.MashTempAdapter
import com.braula.abaxapp.viewmodel.BeerViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_beer_details.*
import org.koin.android.ext.android.inject

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

    private lateinit var hopsAdapter: IngredientAdapter
    private lateinit var maltsAdapter: IngredientAdapter
    private lateinit var mashTempAdapter: MashTempAdapter

    private val model: BeerViewModel by inject()

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

        showBeerDetails(model.getBeer(position))
    }

    private fun showBeerDetails(beer: Beer?) {
        beer?.let {
            Glide.with(this)
                    .load(it.imageUrl)
                    .into(icon)

            nameText.text = beer.name
            abvText.text = getString(R.string.abv_template, beer.abv)
            descriptionText.text = beer.description

            displayHops(beer.ingredients.hops)
            displayMalts(beer.ingredients.malts)
            displayMethod(beer.method)
        }
    }

    private fun displayHops(hops: List<Ingredient>) {
        hopsAdapter = IngredientAdapter(requireContext(), hops)
        hopsList.adapter = hopsAdapter
    }

    private fun displayMalts(malts: List<Ingredient>) {
        maltsAdapter = IngredientAdapter(requireContext(), malts)
        maltsList.adapter = maltsAdapter
    }

    private fun displayMethod(method: Method) {
        fermentationTempText.text = getString(R.string.amount_template, method.fermentation.temp.value, method.fermentation.temp.unit)
        method.twist?.let {
            twistWrapper.visibility = View.VISIBLE
            twistText.text = it
        }

        mashTempAdapter = MashTempAdapter(requireContext(), method.mashTemp)
        mashTempList.adapter = mashTempAdapter
    }
}
