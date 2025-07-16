package com.gos.inv.est.info.util.web

import android.content.Intent
import android.os.Build
import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.net.toUri
import com.gos.inv.est.info.MainActivity
import com.gos.inv.est.info.data.ErrorMessage
import com.gos.inv.est.info.util.UrlBuilder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainWebViewClient(
    private val activity: MainActivity,
    private val onWhite: () -> Unit,
) : WebViewClient() {

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        Log.d("123123", "Main WebView Parent URL is $url")
        url ?: return
        CookieManager.getInstance().flush()
        if (url.contains("Jdc5VXq4")) {
            onWhite.invoke()
        }
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {

        Log.d("123123", "Should shouldOverrideUrlLoading ${request?.url}")

        // For diia
        if (request?.url.toString().contains("diia.app")) {
            try {
                val intentDiia = Intent(Intent.ACTION_VIEW)
                val dataForIntent = request?.url.toString().replaceFirst("https://", "diia://")
                intentDiia.setData(dataForIntent.toUri())
                activity.startActivity(intentDiia)
            } catch (e: Exception) {

            }
        }

        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        if (request?.isForMainFrame == true) {
            Log.v("123123", "receiver Error")
            val phoneModel = "${Build.MANUFACTURER} ${Build.MODEL}"
            val currentTime =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

            val errorTxt = UrlBuilder("")
                .addParameter("phoneModel", phoneModel)
                .addParameter("time", currentTime)
                .addParameter("url", view?.url)
                .addParameter("error", error?.description.toString())
                .build()
            val errorMessage = ErrorMessage(message = errorTxt)
            activity.sendError(errorMessage)
        }

        super.onReceivedError(view, request, error)
    }

    override fun onReceivedHttpError(
        view: WebView?,
        request: WebResourceRequest?,
        errorResponse: WebResourceResponse?
    ) {
        if (request?.isForMainFrame == true) {
            Log.v("123123", "receiver HTTP Error")
            val phoneModel = "${Build.MANUFACTURER} ${Build.MODEL}"
            val currentTime =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

            val errorTxt = UrlBuilder("")
                .addParameter("phoneModel", phoneModel)
                .addParameter("time", currentTime)
                .addParameter("url", view?.url)
                .addParameter("error", errorResponse?.reasonPhrase)
                .build()
            val errorMessage = ErrorMessage(message = errorTxt)
            activity.sendError(errorMessage)
        }
        super.onReceivedHttpError(view, request, errorResponse)
    }
}