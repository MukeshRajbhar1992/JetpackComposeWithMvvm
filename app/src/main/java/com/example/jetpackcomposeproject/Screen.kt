package com.example.jetpackcomposeproject

sealed class Screen(val route :String){
    object SplashScreen:Screen("SplashScreen")
    object LoginScreen:Screen("loginScreen")
    object SignUpScreen:Screen("SignUpScreen")
    object DashBoardScreen:Screen("DashBoardScreen")
}
