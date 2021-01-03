package com.fob.beers.presenter

import android.os.Handler
import android.os.Looper
import com.fob.beers.dao.BeerDAO
import com.fob.beers.interfaces.FavoriteBeerPresenter
import com.fob.beers.interfaces.ViewFavoriteBeerInterface
import com.fob.beers.model.FavoriteBeer
import java.util.concurrent.Executors

class FavoriteBeerImpPresenter (
    private var viewFavoriteBeerInterface: ViewFavoriteBeerInterface,
    private var beerDAO: BeerDAO?
) : FavoriteBeerPresenter {

    private var favoriteBeers: ArrayList<FavoriteBeer> = ArrayList()
    private val executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    override fun getFavoriteBeerList() {

        executor.execute {
            beerDAO?.getFavoriteBeers()?.let { favoriteBeers.addAll(it) }

            handler.post {
                favoriteBeers.let { viewFavoriteBeerInterface.showFavoriteBeers(it) }
                /*
                * its like onPostExecute()
                * */
            }
        }

    }
}