package com.braula.abaxapp.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.braula.abaxapp.model.ApiError
import com.braula.abaxapp.model.Beer
import com.braula.abaxapp.model.service.interactor.BeerInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class BeerViewModel(application: Application) : AndroidViewModel(application), KoinComponent {
    var beersLoaded = false

    val beers = MutableLiveData<ArrayList<Beer>>()
    val error = MutableLiveData<ApiError>()

    private val beerInteractor: BeerInteractor by inject()

    @SuppressLint("CheckResult")
    fun loadBeers() {
        beerInteractor.getBeers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    beers.postValue(it)
                    beersLoaded = true
                },
                {
                    error.postValue(ApiError.LOADING_ERROR)
                }
            )
    }

    fun getBeer(position: Int): Beer? {
        return beers.value?.get(position)
    }
}