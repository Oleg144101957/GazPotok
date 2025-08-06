package ru.gz.po.tok41.ui.screens.splash

import android.app.Activity
import android.content.pm.ActivityInfo
import android.media.SoundPool
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.gz.po.tok41.R
import ru.gz.po.tok41.domain.grey.LoadingState
import ru.gz.po.tok41.navigation.ScreenRoutes
import ru.gz.po.tok41.ui.elements.Background
import ru.gz.po.tok41.ui.elements.MorphingShapeLoadingAnimation
import ru.gz.po.tok41.util.lockOrientation
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

    val soundPool = remember { SoundPool.Builder().setMaxStreams(1).build() }
    val soundId = remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        launcher.launch(permission)


        val soundId = soundPool.load(context, R.raw.loading_sound, 1)

        soundPool.setOnLoadCompleteListener { _, sampleId, _ ->
            if (sampleId == soundId) {
                soundPool.play(
                    soundId,
                    1f, // left volume
                    1f, // right volume
                    1,  // priority
                    -1, // 🔁 loop forever
                    1f  // normal rate
                )
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            soundPool.release()
        }
    }


    LaunchedEffect(state) {
        when (state) {
            LoadingState.InitState -> {
                splashViewModel.load(context, "tjwe4n0ns0sg")
            }

            is LoadingState.ContentState -> {
                val url = URLEncoder.encode(state.url, StandardCharsets.UTF_8.toString())
                val route = "${ScreenRoutes.ContentScreen.route}/$url"
                navController.navigate(route)
            }

            LoadingState.NoNetworkState -> {
                navController.navigate(ScreenRoutes.NoNetworkScreen.route)
            }

            LoadingState.WhiteState -> {
                navController.navigate(ScreenRoutes.HomeScreen.route)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Background(R.drawable.loading_bg)
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.height(128.dp))
            MorphingShapeLoadingAnimation()
        }
    }
}

