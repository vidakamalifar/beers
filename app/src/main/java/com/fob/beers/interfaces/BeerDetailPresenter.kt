package com.fob.beers.interfaces

interface BeerDetailPresenter {
    fun beerDetail(id: Int?)
    fun favoriteIconClicked()
    fun removeFavoriteBeerFromDb()
    fun addFavoriteBeerToDb()
}