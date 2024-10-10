package com.example.jetpackcomposeproject

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeproject.screen.DashBoardField
import com.example.jetpackcomposeproject.screen.LoginScreenField
import com.example.jetpackcomposeproject.screen.SignUpScreenField
import com.example.jetpackcomposeproject.screen.SplashScreenField
import com.example.jetpackcomposeproject.viewModel.MainViewModel

@Composable
fun Navigation(
    viewModel: MainViewModel = viewModel()
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreenField(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreenField(navController = navController,viewModel)
        }
        composable(route = Screen.SignUpScreen.route) {
            SignUpScreenField(navController = navController,viewModel)
        }
        composable(route = Screen.DashBoardScreen.route) {
            DashBoardField(navController = navController)
        }

    }
}