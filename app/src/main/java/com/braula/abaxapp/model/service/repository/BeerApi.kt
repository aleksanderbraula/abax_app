package com.braula.abaxapp.model.service.repository

import com.braula.abaxapp.model.Beer
import io.reactivex.Single
import retrofit2.http.GET

interface BeerApi {

    @GET("/v2/beers")
    fun getBeers(): Single<ArrayList<Beer>>
}
