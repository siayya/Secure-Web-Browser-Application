package com.example.securewebbrowserapplication.navigation

class Routes {
    sealed class Routes(val route: String) {
        object SignIn : Routes("signin")
        object Home : Routes("home")
        object WebView : Routes("webview")
        object History : Routes("history")

    }
}