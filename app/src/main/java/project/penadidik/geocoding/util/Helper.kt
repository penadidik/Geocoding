package project.penadidik.geocoding.util

import android.content.Context
import android.net.ConnectivityManager


object Helper {

    const val KEY_LAT = "lat"
    const val KEY_LON = "lon"
    const val KEY_STATE = "state"
    const val KEY_COUNTRY = "country"

    fun  isNetworkAvailable(context: Context):Boolean {
        val conManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo =conManager.activeNetworkInfo
        return internetInfo!=null && internetInfo.isConnected
    }

}