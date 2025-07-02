package com.sbe.rand.inve.sttwo.ui.screens.splash

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gosapp.sberkas.sber.domain.LoadingState
import com.sbe.rand.inve.sttwo.R
import com.sbe.rand.inve.sttwo.navigation.ScreenRoutes
import com.sbe.rand.inve.sttwo.ui.elements.Background
import com.sbe.rand.inve.sttwo.ui.elements.BallScaleMultipleIndicator
import com.sbe.rand.inve.sttwo.util.lockOrientation
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {

    BackHandler { }

    val state = splashViewModel.liveState.collectAsState().value
    val context = LocalContext.current
    val activity = context as? Activity
    activity?.lockOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    val permission = android.Manifest.permission.POST_NOTIFICATIONS
    val permissionState = remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionState.value = isGranted
        splashViewModel.updatePermissionState(isGranted)
    }

    LaunchedEffect(Unit) {
        launcher.launch(permission)
    }

    LaunchedEffect(state) {
        when (state) {
            LoadingState.InitState -> {
                splashViewModel.load(context,"9cg1d2kaw6ps")
            }

            LoadingState.WhiteState ->{
                navController.navigate(ScreenRoutes.HomeScreen.route)
            }

            is LoadingState.ContentState -> {
                val url = URLEncoder.encode(state.url, StandardCharsets.UTF_8.toString())
                val route = "${ScreenRoutes.ContentScreen.route}/$url"
                navController.navigate(route)
            }

            LoadingState.NoNetworkState -> {
                navController.navigate(ScreenRoutes.NoNetworkScreen.route)
            }
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        Background()
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painterResource(R.drawable.sberrasschet_logo),
                R.drawable.sberrasschet_men.toString(),
                modifier = Modifier.fillMaxWidth(0.8f),
                contentScale = ContentScale.FillWidth
            )
            Spacer(Modifier.height(128.dp))
            BallScaleMultipleIndicator()
        }
    }
}
