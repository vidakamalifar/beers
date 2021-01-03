package com.fob.beers.interfaces

interface BeersPresenter {

    fun getBeerListFromApi()

    fun getBeersListFromDb()

    fun sortByABV()

    fun sortByIBU()

    fun sortByEBC()
}