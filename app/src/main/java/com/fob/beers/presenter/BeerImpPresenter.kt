package com.fob.beers.presenter

import android.os.Handler
import android.os.Looper
import com.fob.beers.dao.BeerDAO
import com.fob.beers.interfaces.BeersPresenter
import com.fob.beers.interfaces.ViewBeersInterface
import com.fob.beers.model.Beer
import com.fob.beers.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class BeerImpPresenter(
    mViewBeersInterface: ViewBeersInterface,
    mBeerDAO: BeerDAO?
) : BeersPresenter {
    private var viewBeersInterface = mViewBeersInterface
    private val beerList: ArrayList<Beer?> = ArrayList()

    private var pageNumber: Int = 1
    private var itemPerPageNumber: Int = 25

    private var beerDAO = mBeerDAO

    private val executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    override fun getBeerListFromApi() {
        val request = ApiService.ApiHandler.retrofitService
        val call = request.getBeersList(
            pageNumber, itemPerPageNumber
        )
        call.enqueue(object : Callback<ArrayList<Beer?>> {

            override fun onResponse(
                call: Call<ArrayList<Beer?>>,
                response: Response<ArrayList<Beer?>>
            ) {
                viewBeersInterface.stopAdapterLoading()
                if (response.isSuccessful) {
                    viewBeersInterface.hideLoading()

                    beerList.addAll(response.body() as ArrayList<Beer>)

                    response.body()?.let { viewBeersInterface.showBeerList(it) }


                    Executors.newSingleThreadExecutor().execute {
                        response.body()?.let { beerDAO?.insert(it) }
                    }

                    if ((response.body() as ArrayList<Beer>).size > 0)
                        pageNumber++


                } else {
                    viewBeersInterface.showServerError()
                }
            }

            override fun onFailure(call: Call<ArrayList<Beer?>>, t: Throwable) {
                viewBeersInterface.stopAdapterLoading()
                viewBeersInterface.hideLoading()
                viewBeersInterface.showServerError()
            }
        })
    }

    override fun getBeersListFromDb() {

        executor.execute {

            beerDAO?.getBeers()?.let { beerList.addAll(it) }

            handler.post {
                viewBeersInterface.showBeerList(beerList)                /*
                * its like onPostExecute()
                * */
            }
        }
    }

    override fun sortByABV() {
        beerList.sortBy { it?.abv }
        viewBeersInterface.showSortResult(beerList)
    }

    override fun sortByIBU() {
        beerList.sortBy { it?.ibu }
        viewBeersInterface.showSortResult(beerList)
    }

    override fun sortByEBC() {
        beerList.sortBy { it?.ebc }
        viewBeersInterface.showSortResult(beerList)
    }
}