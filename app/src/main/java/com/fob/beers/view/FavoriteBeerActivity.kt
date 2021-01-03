package com.fob.beers.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fob.beers.AppApplication
import com.fob.beers.R
import com.fob.beers.adapter.FavoriteBeersAdapter
import com.fob.beers.dao.BeerDAO
import com.fob.beers.interfaces.FavoriteBeerPresenter
import com.fob.beers.interfaces.ViewFavoriteBeerInterface
import com.fob.beers.model.FavoriteBeer
import com.fob.beers.presenter.FavoriteBeerImpPresenter
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteBeerActivity : AppCompatActivity(), ViewFavoriteBeerInterface {

    companion object {
        fun getIntent(context: Context?): Intent? {
            val intent =
                Intent(context, FavoriteBeerActivity::class.java)
            return intent
        }
    }

    private var favoriteBeersAdapter: FavoriteBeersAdapter? = null

    private var beerDAO: BeerDAO? = null

    private var favoriteBeerPresenter: FavoriteBeerPresenter? = null

    private val appApplication: AppApplication get() = applicationContext as AppApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_favorite)

        //set actionbar title
        supportActionBar?.title = getString(R.string.favorite_beer)
        //set back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //db initial
        beerDAO = appApplication.initDb().beerDAO


        //presenter initial
        favoriteBeerPresenter = FavoriteBeerImpPresenter(this, beerDAO)

        getFavoriteBeersFromDb()

        setUpRecyclerView()
    }

    private fun getFavoriteBeersFromDb() {
        (favoriteBeerPresenter as FavoriteBeerImpPresenter).getFavoriteBeerList()
    }

    private fun setUpRecyclerView() {
        rvBeersFavList.layoutManager = LinearLayoutManager(this)
        favoriteBeersAdapter = FavoriteBeersAdapter(this)

        rvBeersFavList.adapter = favoriteBeersAdapter
        rvBeersFavList.isNestedScrollingEnabled = false
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showFavoriteBeers(beerList: ArrayList<FavoriteBeer>) {
        favoriteBeersAdapter?.setData(beerList)

        if (beerList.size > 0)
            tvEmptyState.visibility = View.GONE
        else
            tvEmptyState.visibility = View.VISIBLE


    }
}