package com.braula.abaxapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.braula.abaxapp.R
import com.braula.abaxapp.model.Ingredient
import kotlinx.android.synthetic.main.item_ingredient.view.*

class IngredientAdapter(context: Context, private val items: List<Ingredient>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.item_ingredient, parent, false)

        val ingredient = getItem(position)

        rowView.nameText.text = ingredient.name
        rowView.amountText.text = "${ingredient.amount.value} ${ingredient.amount.unit}"
        if (ingredient.add != null && ingredient.attribute != null) {
            rowView.otherText.text = "(${ingredient.add},${ingredient.attribute})"
        }

        return rowView
    }

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = items.size

}