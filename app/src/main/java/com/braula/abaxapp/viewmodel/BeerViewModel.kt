package com.braula.abaxapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.braula.abaxapp.model.Beer
import com.braula.abaxapp.model.service.ApiError
import com.braula.abaxapp.model.service.interactor.BeerInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BeerViewModel : ViewModel(){
    private var beersLoaded = false
    val beers = MutableLiveData<ArrayList<Beer>>()
    val error = MutableLiveData<ApiError>()

    val beerInteractor = BeerInteractor()

    @SuppressLint("CheckResult")
    fun loadBeers() {
        if (!beersLoaded) {
            beerInteractor.getBeers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        beers.postValue(it)
                        beersLoaded = true;
                    },
                    {
                        error.postValue(ApiError.LOADING_ERROR)
                    }
                )
        }
    }
}