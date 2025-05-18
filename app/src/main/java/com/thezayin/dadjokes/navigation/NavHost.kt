package com.thezayin.dadjokes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thezayin.dadjokes.screens.ai.presentation.AiGenerateScreen
import com.thezayin.dadjokes.screens.home.presentation.HomeScreen
import com.thezayin.dadjokes.screens.onboarding.OnboardingScreen
import com.thezayin.dadjokes.screens.saved.presentation.PreviewJokeScreen
import com.thezayin.dadjokes.screens.saved.presentation.SavedJokesScreen
import com.thezayin.dadjokes.screens.setting.SettingScreen
import com.thezayin.dadjokes.screens.splash.SplashScreen

@Composable
fun NavHost(navController: NavHostController) {
    androidx.navigation.compose.NavHost(
        navController = navController, startDestination = SplashScreenNav
    ) {
        composable<SettingScreenNav> {
            SettingScreen(
                onBackClick = {
                    navController.popBackStack()
                })
        }

        composable<OnboardingScreenNav> {
            OnboardingScreen(
                navigateToHome = {
                    navController.navigate(HomeScreenNav)
                })
        }
        composable<SplashScreenNav> {
            SplashScreen(navigateToOnboarding = {
                navController.navigate(OnboardingScreenNav)
            }, navigateToHome = {
                navController.navigate(HomeScreenNav)
            })
        }

        composable<AiScreenNav> {
            AiGenerateScreen(
                navigateBack = {
                    navController.popBackStack()
                })
        }

        composable<HomeScreenNav> {
            HomeScreen(onNavigateToAiScreen = {
                navController.navigate(AiScreenNav)
            }, onNavigateToSettings = {
                navController.navigate(SettingScreenNav)
            }, onNavigateToSavedJokes = {
                navController.navigate(SaveScreenNav)
            })
        }

        composable<SaveScreenNav> {
            SavedJokesScreen(navigateToPreview = {
                navController.navigate(PreviewScreenNav)
            }, navigateBack = {
                navController.popBackStack()
            })
        }

        composable<PreviewScreenNav> {
            PreviewJokeScreen(
                onNavigateBack = {
                    navController.popBackStack()
                })
        }
    }
}