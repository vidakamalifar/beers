package com.fob.beers.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fob.beers.AppApplication
import com.fob.beers.R
import com.fob.beers.adapter.BeersAdapter
import com.fob.beers.adapter.BeersAdapterInterface
import com.fob.beers.adapter.RecyclerViewEndlessAdapter
import com.fob.beers.dao.BeerDAO
import com.fob.beers.interfaces.BeersPresenter
import com.fob.beers.interfaces.ViewBeersInterface
import com.fob.beers.model.Beer
import com.fob.beers.presenter.BeerImpPresenter
import com.fob.beers.dialog.SortDialog
import com.fob.beers.interfaces.DialogOnClickInterface
import com.fob.beers.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BeersAdapterInterface, ViewBeersInterface,
    DialogOnClickInterface {
    val TAG = "MainActivity"
    val ABV = "ABV"
    val IBU = "IBU"
    val EBC = "EBC"
    private var beerList: ArrayList<Beer?> = ArrayList()
    private var beersAdapter: BeersAdapter? = null

    private var beerDAO: BeerDAO? = null

    private var beerPresenter: BeersPresenter? = null

    private var isNetWorkAvailable : Boolean = false

    private val appApplication: AppApplication get() = applicationContext as AppApplication


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //db initial
        beerDAO = appApplication.initDb().beerDAO

        //presenter initial
        beerPresenter = BeerImpPresenter(this, beerDAO)

        setUpRecyclerView()

        isNetWorkAvailable = Utils.checkNetwork(this)

        if (beerList.size < 1) {
            if (isNetWorkAvailable)
                beerPresenter?.getBeerListFromApi()
            else
                beerPresenter?.getBeersListFromDb()
        }
    }

    private fun setUpRecyclerView() {
        rvBeersList.layoutManager = LinearLayoutManager(this)
        beersAdapter = BeersAdapter(this, beerList, rvBeersList,
            object : RecyclerViewEndlessAdapter.OnLoadMoreListener {
                override fun onLoadMore() {
                    if (isNetWorkAvailable) {
                        beersAdapter?.startLoading()
                        beerPresenter?.getBeerListFromApi()
                    }
                }
            })
        beersAdapter?.setListener(this)
        rvBeersList.adapter = beersAdapter
        rvBeersList.isNestedScrollingEnabled = false
    }

    override fun beerClicked(beer: Beer) {
        startActivity(beer.id?.let { BeerDetailActivity.getIntent(this, it) })
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun stopAdapterLoading() {
        beersAdapter?.stopLoading()
    }

    override fun startAdapterLoading() {
        beersAdapter?.startLoading()
    }

    override fun showServerError() {
        Utils.showSnackBar(
            rootMain,
            this@MainActivity.resources.getString(R.string.server_error),
            this@MainActivity
        )
    }

    override fun showBeerList(beers: ArrayList<Beer?>) {
        this.beerList.addAll(beers)
        beersAdapter?.setData(beerList)
    }

    override fun showSortResult(beers: ArrayList<Beer?>) {
        this.beerList.clear()
        this.beerList.addAll(beers)

        beersAdapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; We can access to searchView
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //sort icon in the menu
        if (item.itemId == R.id.app_bar_sort) {
            openSortDialog()
        } else if (item.itemId == R.id.app_bar_favorite)
            goToFavoriteActivity()
        return super.onOptionsItemSelected(item)
    }

    private fun goToFavoriteActivity() {
        startActivity(FavoriteBeerActivity.getIntent(this))
    }

    private fun openSortDialog() {
        SortDialog(this, this)
    }

    override fun sortList(sortType: String?) {
        when {
            sortType.equals(ABV) -> {
                beerPresenter?.sortByABV()
            }
            sortType.equals(IBU) -> {
                beerPresenter?.sortByIBU()
            }
            sortType.equals(EBC) -> {
                beerPresenter?.sortByEBC()
            }
        }
    }

}