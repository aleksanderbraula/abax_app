package com.braula.abaxapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.braula.abaxapp.R
import com.braula.abaxapp.model.Beer
import com.bumptech.glide.Glide

class BeerAdapter(private val context: Context): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    var items = arrayListOf<Beer>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val viewHolder: BeerViewHolder
        val rowView: View?

        if (convertView == null) {
            rowView = inflater.inflate(R.layout.item_beer, parent, false)
            viewHolder = BeerViewHolder(rowView)
            rowView.tag = viewHolder
        } else {
            rowView = convertView
            viewHolder = rowView.tag as BeerViewHolder
        }

        val beer = getItem(position)

        Glide.with(context)
                .load(beer.imageUrl)
                .placeholder(R.drawable.icons8_beer_48)
                .into(viewHolder.icon)

        viewHolder.nameText.text = beer.name
        viewHolder.abvText.text = context.getString(R.string.abv_template, beer.abv)

        return rowView
    }

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = items.size
}

class BeerViewHolder(view: View?) {
    val icon: ImageView = view?.findViewById(R.id.icon) as ImageView
    val nameText: TextView = view?.findViewById(R.id.nameText) as TextView
    val abvText: TextView = view?.findViewById(R.id.abvText) as TextView
}
