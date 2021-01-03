package com.fob.beers.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fob.beers.R
import com.fob.beers.extensions.inflate
import com.fob.beers.model.Beer
import com.fob.beers.model.FavoriteBeer
import kotlinx.android.synthetic.main.item_favorite_beer.view.*

class FavoriteBeersAdapter (private val context: Context?) : RecyclerView.Adapter<FavoriteBeersAdapter.ViewHolder>() {

    private var favoriteBeers: ArrayList<FavoriteBeer> = ArrayList()

    override fun getItemCount(): Int {
        return favoriteBeers.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.item_favorite_beer, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = favoriteBeers[position]

        context?.let {
            Glide
                .with(it)
                .load(beer.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.background_pre_image_loading)
                .into(holder.itemView.ivBeer)
        }

        holder.itemView.tvBeerName.text = beer.name
        holder.itemView.tvBeerTagLine.text = beer.tagLine
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private var beer: Beer? = null

    }

    fun setData(list: ArrayList<FavoriteBeer>) {
        favoriteBeers = list
        notifyDataSetChanged()
    }
}