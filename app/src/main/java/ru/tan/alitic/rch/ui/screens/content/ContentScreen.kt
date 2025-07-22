package ru.tan.alitic.rch.ui.screens.content

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import ru.tan.alitic.rch.MainActivity
import ru.tan.alitic.rch.R
import ru.tan.alitic.rch.data.NetworkCheckerRepositoryImpl
import ru.tan.alitic.rch.navigation.ScreenRoutes
import ru.tan.alitic.rch.util.lockOrientation
import ru.tan.alitic.rch.util.web.MainCustomWebView
import ru.tan.alitic.rch.util.web.MainWebChromeClient


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentScreen(
    navigationController: NavHostController,
    paddingValues: PaddingValues,
    url: String
) {

    val onWhite = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val activity = context as MainActivity
    activity.lockOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)

    val webView = remember { mutableStateOf<MainCustomWebView?>(null) }
    val destination = remember { mutableStateOf(url) }
    val networkChecker = NetworkCheckerRepositoryImpl(context)

    val fileChooserLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri> ->
        webView.value?.let { webViewInstance ->
            (webViewInstance.webChromeClient as? MainWebChromeClient)?.onFileCallback(uris.toTypedArray())
        }
    }

    BackHandler {
        val childWebView = activity.currentChildWebView
        if (childWebView != null) {
            if (childWebView.canGoBack()) {
                childWebView.goBack()
            } else {
                (childWebView.parent as? ViewGroup)?.removeView(childWebView)
                activity.currentChildWebView = null
            }
        } else {
            val mainWebView = webView.value
            if (mainWebView?.canGoBack() == true) {
                mainWebView.goBack()
            }
        }
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        destination.value.let { url ->
            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding(),
                factory = {
                    MainCustomWebView(
                        context = activity,
                        content = fileChooserLauncher,
                        onWhite = {
                            onWhite.value = true
                        }
                    ).apply {
                        webView.value = this
                        if (networkChecker.isConnectionAvailable()) {
                            loadUrl(url)
                        } else {
                            navigationController.navigate(ScreenRoutes.NoNetworkScreen.route)
                        }
                    }
                }
            )
        }

        if (onWhite.value) {
            Button(
                onClick = {
                    navigationController.navigate(ScreenRoutes.HomeScreen.route)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 64.dp, end = 16.dp)
            ) {
                Text(text = stringResource(R.string.try_again))
            }
        }
    }
}
