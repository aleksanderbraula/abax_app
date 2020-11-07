package com.braula.abaxapp.model

import com.google.gson.annotations.SerializedName

data class Beer(
    val id: String,
    val name: String,
    @SerializedName(value = "image_url") val imageUrl: String,
    val abv: String,
    val description: String,
    val ingredients: Ingredients,
    val method: Method
)

data class Ingredients(
    val hops: List<Ingredient>,
    @SerializedName(value = "malt") val malts: List<Ingredient>
)

data class Ingredient(
    val name: String,
    val amount: Amount,
    val add: String,
    val attribute: String
)

data class Amount(
    val value: Float,
    val unit: String
)

data class Method(
    @SerializedName(value = "mash_temp") val mashTemp: List<MashTemp>,
    val fermentation: Fermentation,
    val twist: String?
)

class Fermentation(
    val temp: Amount
)

data class MashTemp(
    val temp: Amount,
    val duration: Int
)
