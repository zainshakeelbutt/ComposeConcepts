package com.o5appstudio.composeconcepts.composefeatures.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItems(val route:String, val icon:ImageVector, val title:String) {

    data object Features : BottomNavItems("features", Icons.Filled.CheckCircle, "Feat")
    data object Home : BottomNavItems("home", Icons.Filled.Home, "Home")
    data object Search : BottomNavItems("search", Icons.Filled.Search, "Search")
    data object Profile : BottomNavItems("profile", Icons.Filled.Person, "Profile")


}