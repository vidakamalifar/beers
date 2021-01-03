package com.fob.beers.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fob.beers.R
import com.fob.beers.extensions.inflate
import com.fob.beers.model.Beer
import kotlinx.android.synthetic.main.item_beer.view.*


class BeersAdapter(
    context: Context?,
    items: ArrayList<Beer?>,
    recyclerView: RecyclerView?,
    onLoadMoreListener: OnLoadMoreListener?
) : RecyclerViewEndlessAdapter<Beer?>(items, context, recyclerView, onLoadMoreListener) {

    private lateinit var listener: BeersAdapterInterface

    override fun mOnCreateViewHolder(
        viewGroup: ViewGroup?,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflatedView = viewGroup?.inflate(R.layout.item_beer, false)
        return inflatedView?.let { ViewHolder(it) }!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val beer = items[position]

            context?.let {
                Glide
                    .with(it)
                    .load(beer?.imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.background_pre_image_loading)
                    .into(holder.itemView.ivBeer)
            }


            holder.itemView.tvBeerName.text = beer?.name

            holder.itemView.setOnClickListener {
                beer?.let { it1 -> listener.beerClicked(it1) }
            }

        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private var beer: Beer? = null

    }

    fun setListener(listener: BeersAdapterInterface) {
        this.listener = listener
    }

    fun setData(list: ArrayList<Beer?>) {
        items.addAll(list)
        notifyDataSetChanged()
    }
}

interface BeersAdapterInterface {
    fun beerClicked(beer: Beer)
}