package com.fob.beers.interfaces

import com.fob.beers.model.Beer

interface ViewBeerDetailInterface {
    fun showData(beer: Beer)
    fun showFavoriteBeerIcon(isFavorite: Boolean)
}