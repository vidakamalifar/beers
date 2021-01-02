package com.fob.beers.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window

abstract class BaseDialog(context: Context) {

    internal lateinit var parentView: View
    private var dialog: Dialog = Dialog(context)

    init {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(true)
    }

    fun setParentView(p: View) {
        parentView = p
    }

    fun show() {
        dialog.setContentView(parentView)
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }

    fun getDialog(): Dialog? {
        return dialog
    }
}