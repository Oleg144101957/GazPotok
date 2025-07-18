package gf.na.chisle.nia.navigation

sealed class ScreenRoutes(val route: String) {

    data object SplashScreen : ScreenRoutes(SPLASH_SCREEN)
    data object HomeScreen : ScreenRoutes(HOME_SCREEN)
    data object AboutScreen : ScreenRoutes(ABOUT_SCREEN)
    data object NoNetworkScreen : ScreenRoutes(NO_NETWORK_SCREEN)
    data object SettingsScreen : ScreenRoutes(SETTINGS_SCREEN)
    data object ContentScreen : ScreenRoutes(CONTENT_SCREEN)
    data object ModelingScreen : ScreenRoutes(MODELING_SCREEN)
    data object AddInfoScreen : ScreenRoutes(ADD_INFO_SCREEN)
    data object HistoryScreen : ScreenRoutes(HISTORY_SCREEN)
    data object AnalyticScreen : ScreenRoutes(ANALYTIC_SCREEN)


    companion object {
        private const val QUIZ_SCREEN = "Quiz_Screen"
        private const val SPLASH_SCREEN = "Splash_Screen"
        private const val HOME_SCREEN = "Home_Screen"
        private const val ABOUT_SCREEN = "About_Screen"
        private const val NO_NETWORK_SCREEN = "No_Network_Screen"
        private const val SETTINGS_SCREEN = "Settings_Screen"
        private const val CONTENT_SCREEN = "Content_Screen"
        private const val MODELING_SCREEN = "Modeling_Screen"
        private const val ADD_INFO_SCREEN = "Add_Info_Screen"
        private const val HISTORY_SCREEN = "History_Screen"
        private const val ANALYTIC_SCREEN = "Analytic_Screen"
    }
}