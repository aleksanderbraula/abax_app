package com.braula.abaxapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.braula.abaxapp.R
import com.braula.abaxapp.model.Beer
import kotlinx.android.synthetic.main.item_beer.view.*

class BeerAdapter(context: Context): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    var items = arrayListOf<Beer>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.item_beer, parent, false)

        val beer = getItem(position)

        rowView.name.text = beer.name

        return rowView
    }

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = items.size

}