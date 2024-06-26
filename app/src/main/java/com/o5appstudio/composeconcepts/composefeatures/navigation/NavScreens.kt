package com.o5appstudio.composeconcepts.composefeatures.navigation

sealed class NavScreens(val screenName : String) {
    object ScreenOne : NavScreens("screenOne")
    object Features : NavScreens("features")
}