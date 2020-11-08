package com.braula.abaxapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.braula.abaxapp.R
import com.braula.abaxapp.model.Ingredient
import com.braula.abaxapp.model.MashTemp
import kotlinx.android.synthetic.main.item_ingredient.view.*
import kotlinx.android.synthetic.main.item_mash_temp.view.*

class MashTempAdapter (context: Context, private val items: List<MashTemp>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.item_mash_temp, parent, false)

        val mashTemp = getItem(position)

        rowView.mashTempText.text = "${mashTemp.temp.value}${mashTemp.temp.unit} for ${mashTemp.duration} minutes"

        return rowView
    }

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = items.size

}