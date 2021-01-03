package com.fob.beers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fob.beers.R


abstract class RecyclerViewEndlessAdapter<T>(
    var items: ArrayList<T?>,
    var context: Context?,
    var recyclerView: RecyclerView?,
    onLoadMoreListener: OnLoadMoreListener?
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_ITEM = 10
    private val VIEW_PROGRESS = 11
    private var isLoading = false
    private var pastVisibleItems = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    interface OnLoadMoreListener
    {
        fun onLoadMore()
    }

    private val onLoadMoreListener: OnLoadMoreListener?
    protected abstract fun mOnCreateViewHolder(
        viewGroup: ViewGroup?,
        viewType: Int
    ): RecyclerView.ViewHolder

    fun startLoading() {
        items.add(null)
        notifyItemInserted(items.size)
    }

    fun stopLoading() {
        isLoading = false
        removeNullFromList()
    }

    private fun removeNullFromList() {
        if (items.size > 0 && items[items.size - 1] == null) {
            items.removeAt(items.size - 1)
            notifyItemRemoved(items.size)
        }
    }

    private fun getItemAtPosition(position: Int): Any? {
        return items[position]
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItemAtPosition(position) != null) VIEW_ITEM else VIEW_PROGRESS
    }

    private class ProgressViewHolder(v: View) :
        RecyclerView.ViewHolder(v) {
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == VIEW_PROGRESS) {
            val itemView: View = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_progress, viewGroup, false)
            ProgressViewHolder(itemView)
        } else {
            mOnCreateViewHolder(viewGroup, viewType)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    init {
        this.context = context!!
        isLoading = false
        this.recyclerView = recyclerView
        this.onLoadMoreListener = onLoadMoreListener
        if (recyclerView?.layoutManager is LinearLayoutManager) {
            val linearLayoutManager =
                recyclerView?.layoutManager as LinearLayoutManager?
            recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView,
                    dx: Int,
                    dy: Int
                ) {
                    if (dy > 0) //check for scroll down
                    {
                        visibleItemCount = linearLayoutManager!!.childCount
                        totalItemCount = linearLayoutManager.itemCount
                        pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()
                        if (!isLoading) {
                            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                                onLoadMoreListener?.onLoadMore()
                                isLoading = true
                            }
                        }
                    } else if (linearLayoutManager!!.reverseLayout) {
                        visibleItemCount = linearLayoutManager.childCount
                        totalItemCount = linearLayoutManager.itemCount
                        pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()
                        if (!isLoading) {
                            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                                onLoadMoreListener?.onLoadMore()
                                isLoading = true
                            }
                        }
                    }
                }
            })
        }
    }
}
