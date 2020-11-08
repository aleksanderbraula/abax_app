package com.braula.abaxapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.braula.abaxapp.R
import com.braula.abaxapp.model.Beer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_beer.view.*

class BeerAdapter(private val context: Context): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    var items = arrayListOf<Beer>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.item_beer, parent, false)

        val beer = getItem(position)

        Glide.with(context)
                .load(beer.imageUrl)
                .placeholder(R.drawable.icons8_beer_48)
                .into(rowView.icon)

        rowView.name.text = beer.name
        rowView.abvText.text = "${beer.abv}%"

        return rowView
    }

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = items.size

}