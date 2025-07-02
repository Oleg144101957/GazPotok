package com.sbe.rand.inve.sttwo.navigation

sealed class ScreenRoutes(val route: String) {

    data object SplashScreen : ScreenRoutes(SPLASH_SCREEN)
    data object HomeScreen : ScreenRoutes(HOME_SCREEN)
    data object AboutScreen : ScreenRoutes(ABOUT_SCREEN)
    data object NoNetworkScreen : ScreenRoutes(NO_NETWORK_SCREEN)
    data object SettingsScreen : ScreenRoutes(SETTINGS_SCREEN)
    data object OnboardingScreen : ScreenRoutes(ONBOARDING_SCREEN)
    data object CurrencyRatesScreen : ScreenRoutes(CURRENCY_RATES_SCREEN)
    data object InvestmentCalculatorScreen : ScreenRoutes(INVESTMENT_CALCULATION_SCREEN)
    data object LoanCalculatorScreen : ScreenRoutes(LOAN_CALCULATION_SCREEN)
    data object MyLoanTrackerScreen : ScreenRoutes(MY_LOAN_TRACKER_SCREEN)
    data object SavingsGoalsScreen : ScreenRoutes(SAVING_GOALS_SCREEN)
    data object ContentScreen : ScreenRoutes(CONTENT_SCREEN)
    data object QuizScreen : ScreenRoutes(QUIZ_SCREEN)
    data object ResultScreen : ScreenRoutes(RESULT_SCREEN)



    companion object {
        private const val QUIZ_SCREEN = "Quiz_Screen"
        private const val SPLASH_SCREEN = "Splash_Screen"
        private const val HOME_SCREEN = "Home_Screen"
        private const val ABOUT_SCREEN = "About_Screen"
        private const val NO_NETWORK_SCREEN = "No_Network_Screen"
        private const val SETTINGS_SCREEN = "Settings_Screen"
        private const val ONBOARDING_SCREEN = "Onboarding_Screen"
        private const val CURRENCY_RATES_SCREEN = "Currency_Rates_Screen"
        private const val INVESTMENT_CALCULATION_SCREEN = "Investment_Calculator_Screen"
        private const val LOAN_CALCULATION_SCREEN = "Loan_Calculator_Screen"
        private const val MY_LOAN_TRACKER_SCREEN = "My_Loan_Tracker_Screen"
        private const val SAVING_GOALS_SCREEN = "Savings_Goals_Screen"
        private const val CONTENT_SCREEN = "Content_Screen"
        private const val RESULT_SCREEN = "Result_Screen"

    }
}