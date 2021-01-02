package com.fob.beers.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.fob.beers.R
import com.google.android.material.snackbar.Snackbar


class Utils {

    companion object {
        fun showSnackBar(v: View?, snackBarText: String?, context: Context?) {
            if (v == null || snackBarText == null) {
                return
            }

            val snackBar =
                Snackbar.make(v, snackBarText, 4000) //Snackbar.LENGTH_LONG
                    .setAction("Action", null)
            val sbView = snackBar.view
            context?.let { ContextCompat.getColor(it, R.color.blue) }?.let {
                sbView.setBackgroundColor(
                    it
                )
            }
            val snackText = snackBar.view.findViewById<TextView>(
                com.google.android.material.R.id.snackbar_text
            )
            snackText.textSize = 14f
            snackBar.show()


        }
        fun checkNetwork(context: Context?): Boolean {
            val connectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val nw = connectivityManager.activeNetwork ?: return false
                val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
                return when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    //for other device how are able to connect with Ethernet
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    //for check internet over Bluetooth
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                    else -> false
                }
        }
    }
}