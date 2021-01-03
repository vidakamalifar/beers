package com.fob.beers.interfaces

import com.fob.beers.model.Beer

interface ViewBeersInterface {

    fun showLoading()

    fun hideLoading()

    fun stopAdapterLoading()

    fun startAdapterLoading()

    fun showServerError()

    fun showBeerList(beerList: ArrayList<Beer?>)

    fun showSortResult(beerList: ArrayList<Beer?>)

}