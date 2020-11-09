package com.braula.abaxapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.braula.abaxapp.R
import com.braula.abaxapp.model.Ingredient

class IngredientAdapter(private val context: Context, private val items: List<Ingredient>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val viewHolder: IngredientViewHolder
        val rowView: View?

        if (convertView == null) {
            rowView = inflater.inflate(R.layout.item_ingredient, parent, false)
            viewHolder = IngredientViewHolder(rowView)
            rowView.tag = viewHolder
        } else {
            rowView = convertView
            viewHolder = rowView.tag as IngredientViewHolder
        }

        val ingredient = getItem(position)

        viewHolder.nameText.text = ingredient.name
        viewHolder.amountText.text = context.getString(R.string.amount_template, ingredient.amount.value, ingredient.amount.unit)
        if (ingredient.add != null && ingredient.attribute != null) {
            viewHolder.otherText.text = context.getString(R.string.other_template, ingredient.add, ingredient.attribute)
        }

        return rowView
    }

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = items.size
}

class IngredientViewHolder(view: View?) {
    val nameText: TextView = view?.findViewById(R.id.nameText) as TextView
    val amountText: TextView = view?.findViewById(R.id.amountText) as TextView
    val otherText: TextView = view?.findViewById(R.id.otherText) as TextView
}
