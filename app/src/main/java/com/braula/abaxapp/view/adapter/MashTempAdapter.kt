package com.braula.abaxapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.braula.abaxapp.R
import com.braula.abaxapp.model.MashTemp

class MashTempAdapter (private val context: Context, private val items: List<MashTemp>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val viewHolder: MashTempViewHolder
        val rowView: View?

        if (convertView == null) {
            rowView = inflater.inflate(R.layout.item_mash_temp, parent, false)
            viewHolder = MashTempViewHolder(rowView)
            rowView.tag = viewHolder
        } else {
            rowView = convertView
            viewHolder = rowView.tag as MashTempViewHolder
        }

        val mashTemp = getItem(position)

        viewHolder.mashTempText.text = context.getString(R.string.mash_temp_template, mashTemp.temp.value, mashTemp.temp.unit, mashTemp.duration)

        return rowView
    }

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = items.size
}

class MashTempViewHolder(view: View?) {
    val mashTempText: TextView = view?.findViewById(R.id.mashTempText) as TextView
}
