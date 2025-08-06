package ru.gz.po.tok41.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import ru.gz.po.tok41.domain.grey.NetworkCheckerRepository
import javax.inject.Inject

class NetworkCheckerRepositoryImpl @Inject constructor(private val context: Context) :
    NetworkCheckerRepository {

    override fun isConnectionAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}