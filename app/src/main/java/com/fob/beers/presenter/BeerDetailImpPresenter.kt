package com.fob.beers.presenter

import android.os.Handler
import android.os.Looper
import com.fob.beers.dao.BeerDAO
import com.fob.beers.interfaces.BeerDetailPresenter
import com.fob.beers.interfaces.ViewBeerDetailInterface
import com.fob.beers.model.Beer
import com.fob.beers.model.FavoriteBeer
import com.google.gson.Gson
import java.util.concurrent.Executors

class BeerDetailImpPresenter(
    private var viewBeerDetailInterface: ViewBeerDetailInterface,
    private var beerDAO: BeerDAO?
) : BeerDetailPresenter {

    private var beer: Beer? = null
    private val executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())
    private var favoriteBeer: FavoriteBeer? = null

    override fun beerDetail(id: Int?) {

        executor.execute {
            beer = beerDAO?.getBeerDetail(id)
            favoriteBeer = beerDAO?.getFavoriteBeerDetail(id)

            handler.post {
                beer?.let { viewBeerDetailInterface.showData(it) }

                favoriteBeer?.let {
                    it.isFavorite?.let { it1 ->
                        viewBeerDetailInterface.showFavoriteBeerIcon(
                            it1
                        )
                    }
                }
                /*
                * its like onPostExecute()
                * */
            }
        }
    }

    private fun updateFavoriteDb() {

        executor.execute {
            /*
            * its like doInBackground()
            * */
            favoriteBeer?.let { beerDAO?.updateFavoriteBeer(it) }
        }
    }

    override fun favoriteIconClicked() {
        if (favoriteBeer != null) {
            if (favoriteBeer?.isFavorite!!) {
                beer?.isFavorite = false
                removeFavoriteBeerFromDb()
                viewBeerDetailInterface.showFavoriteBeerIcon(false)
            } else {
                beer?.isFavorite = true
                favoriteBeer?.isFavorite = true
                updateFavoriteDb()
                viewBeerDetailInterface.showFavoriteBeerIcon(true)
            }
        } else {
            beer?.isFavorite = true

            addFavoriteBeerToDb()
            viewBeerDetailInterface.showFavoriteBeerIcon(true)
        }
    }

    override fun removeFavoriteBeerFromDb() {
        executor.execute {
            favoriteBeer?.let { beerDAO?.deleteFavoriteBeers(it) }
        }
    }

    override fun addFavoriteBeerToDb() {
        favoriteBeer = FavoriteBeer(beer?.id, beer?.name, beer?.tagLine, beer?.imageUrl, beer?.isFavorite)
        executor.execute {
            favoriteBeer?.let { beerDAO?.insertFavoriteBeer(it) }
        }
    }
}
