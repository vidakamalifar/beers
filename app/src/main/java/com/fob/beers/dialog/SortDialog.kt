package com.fob.beers.dialog

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.fob.beers.R
import com.fob.beers.interfaces.DialogOnClickInterface
import kotlinx.android.synthetic.main.dialog_sort.view.*

class SortDialog(
    private val context: Context,
    private val listener: DialogOnClickInterface
) : BaseDialog(context), View.OnClickListener {


    private var sortType: String? = null

    private val ABV = "ABV"
    private val IBU = "IBU"
    private val EBC = "EBC"

    init {
        showDialog()
    }

    private fun showDialog() {
        parentView = LayoutInflater.from(context).inflate(R.layout.dialog_sort, null)
        setParentView(parentView)
        getDialog()?.window?.setWindowAnimations(R.style.DialogAnimation)
        show()

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(getDialog()?.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.BOTTOM
        lp.windowAnimations = R.style.DialogAnimation
        getDialog()?.window!!.attributes = lp

        setViews()

    }

    private fun setViews() {
        setOnClicks()
    }

    private fun setOnClicks() {
        parentView.rbABV.setOnClickListener(this)
        parentView.rbIBU.setOnClickListener(this)
        parentView.rbEBC.setOnClickListener(this)
        parentView.bCancel.setOnClickListener(this)
        parentView.bDone.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.rbABV -> sortType = ABV

            R.id.rbIBU -> sortType = IBU

            R.id.rbEBC -> sortType = EBC

            R.id.bCancel -> dismiss()

            R.id.bDone -> {
                listener.sortList(sortType)
                dismiss()
            }
        }
    }
}