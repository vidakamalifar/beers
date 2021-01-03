package com.fob.beers.interfaces

import com.fob.beers.model.Beer
import com.fob.beers.model.FavoriteBeer

interface ViewFavoriteBeerInterface {

    fun showFavoriteBeers(beerList: ArrayList<FavoriteBeer>)

}