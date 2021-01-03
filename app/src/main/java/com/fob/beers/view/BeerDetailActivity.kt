package com.fob.beers.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.fob.beers.AppApplication
import com.fob.beers.R
import com.fob.beers.dao.BeerDAO
import com.fob.beers.interfaces.BeerDetailPresenter
import com.fob.beers.interfaces.ViewBeerDetailInterface
import com.fob.beers.model.Beer
import com.fob.beers.presenter.BeerDetailImpPresenter
import kotlinx.android.synthetic.main.activity_beer_detail.*


class BeerDetailActivity : AppCompatActivity(), ViewBeerDetailInterface {

    private var id: Int? = null

    private var beerDAO: BeerDAO? = null

    private var beerDetailPresenter: BeerDetailPresenter? = null

    private val appApplication: AppApplication get() = applicationContext as AppApplication

    companion object {
        fun getIntent(context: Context?, id: Int): Intent? {
            val intent =
                Intent(context, BeerDetailActivity::class.java)
                    .putExtra(PASS_BEER_ID, id)
            return intent
        }

        const val PASS_BEER_ID = "PASS_BEER_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_beer_detail)

        supportActionBar?.hide()

        intent.let {
            id = it.getIntExtra(PASS_BEER_ID, 0)
        }

        //db initial
        beerDAO = appApplication.initDb().beerDAO

        //presenter initial
        beerDetailPresenter = BeerDetailImpPresenter(this, beerDAO)

        getBeerDetail()

        setListener()
    }

    private fun getBeerDetail() {
        (beerDetailPresenter as BeerDetailImpPresenter).beerDetail(id)
    }

    private fun setListener() {
        ivBack.setOnClickListener { onBackPressed() }

        ivFavorite.setOnClickListener {
            beerDetailPresenter?.favoriteIconClicked()
        }
    }

    override fun showData(beer: Beer) {
        Glide
            .with(this)
            .load(beer.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.background_pre_image_loading)
            .into(ivBeer)

        tvBeerName.text = beer.name
        tvTagLine.text = beer.tagLine
        tvFirstBrewed.text = beer.firstBrewed
        tvVolume.text = "${beer.volume.value} ${beer.volume.unit} "
        tvDescription.text = beer.description

        setTextViewFoodPairingListValues(beer.foodPairingList)

        tvAbv.text = setTitleBold(getString(R.string.ABV)+": " + "${beer.abv}",3)
        tvIbu.text = setTitleBold(getString(R.string.IBU)+": "+ "${beer.ibu}",3)
        tvEbc.text = setTitleBold(getString(R.string.EBC) +": "+ "${beer.ebc}",3)

        tvSrm.text = setTitleBold(getString(R.string.SRM) + ": "+"${beer.srm}",  3)
        tvPh.text = setTitleBold(getString(R.string.PH)+ ": "+"${beer.ph}",2)
        tvAttenuationLevel.text =
            setTitleBold(getString(R.string.attenuation_level) + ": "+"${beer.attenuationLevel}",17)

        tvBrewersTips.text = beer.brewersTips
        tvContributedBy.text = beer.contributedBy
    }

    private fun setTitleBold(text: String,end: Int): SpannableString {
        val spannable = SpannableString(text)
        spannable.setSpan(
            ForegroundColorSpan(Color.BLUE),
            0, end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            StyleSpan(BOLD),
            0, end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return spannable

    }

    private fun setTextViewFoodPairingListValues(values: ArrayList<String>?) {
        //Variable to hold all the values
        var output: String? = ""
        for (i in 0 until values?.size!!) {
            //Append all the values to a string
            if (i == 0)
                output += values[i]
            else if (i > 0)
                output += " ," + values[i]
        }

        //Set the textview to the output string
        tvBeerFormula.text = output
    }

    override fun showFavoriteBeerIcon(isFavorite: Boolean) {
        if (isFavorite) {
            ivFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            ivFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }
}