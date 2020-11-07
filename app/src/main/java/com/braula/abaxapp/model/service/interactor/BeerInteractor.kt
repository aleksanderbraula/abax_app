package com.braula.abaxapp.model.service.interactor

import com.braula.abaxapp.model.Beer
import com.braula.abaxapp.model.service.ApiFactory
import io.reactivex.Single

class BeerInteractor {

    private val beerApi = ApiFactory.beerApi

    fun getBeers(): Single<ArrayList<Beer>> {
        return beerApi.getBeers()
    }

}