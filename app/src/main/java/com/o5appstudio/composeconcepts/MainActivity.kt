package com.o5appstudio.composeconcepts

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState


import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.o5appstudio.composeconcepts.composefeatures.Features

import com.o5appstudio.composeconcepts.composefeatures.bottomnavigation.BottomNavItems
import com.o5appstudio.composeconcepts.composefeatures.bottomnavigation.HomeScreen
import com.o5appstudio.composeconcepts.composefeatures.bottomnavigation.ProfileScreen
import com.o5appstudio.composeconcepts.composefeatures.bottomnavigation.SearchScreen
import com.o5appstudio.composeconcepts.composefeatures.navigation.NavScreens
import com.o5appstudio.composeconcepts.composefeatures.navigation.ScreenOne
import com.o5appstudio.composeconcepts.testing.SharedData
import com.o5appstudio.composeconcepts.testing.TestingScreenTwo
import com.o5appstudio.composeconcepts.testing.TestingTwo
import com.o5appstudio.composeconcepts.testing.viewmodels.TestingViewModel

import com.o5appstudio.composeconcepts.tweetyapp.api.TweetyApi

import com.o5appstudio.composeconcepts.ui.theme.ComposeConceptsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tweetyApi: TweetyApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        CoroutineScope(Dispatchers.IO).launch {
//            delay(1000)
//            DataManager.loadAssetsFromFile(applicationContext)
//        }

        setContent {
            ComposeConceptsTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    NavMainScreen()
                    

                    
                }


            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavMainScreen() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val sheetState = rememberModalBottomSheetState()
    val showBottomSheet = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current.applicationContext

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            DrawerContent(
                coroutineScope = coroutineScope,
                drawerState = drawerState,
                navController = navController
            )
        }) {
        Scaffold(
            topBar = {
                TopAppBar(drawerState = drawerState, coroutineScope = coroutineScope)
            },
            bottomBar = { BottomNavigationBar(navController = navController,) },

            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        showBottomSheet.value = true
                    },
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "")
                }
            },
            floatingActionButtonPosition = FabPosition.End,

            ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
//                MainScreens(navController, context)
                TestingScreen()
            }
        }

        if (showBottomSheet.value) {
            BottomSheetContent(showBottomSheet = showBottomSheet, sheetState = sheetState)
        }
    }


}


@Composable
fun TestingScreen(testingViewModel : TestingViewModel= hiltViewModel()){
    val context = LocalContext.current.applicationContext
    val counter = testingViewModel.numList.collectAsState()

    Box(
        contentAlignment = Alignment.Center,

    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){


            Text(text = counter.value.toString(), fontSize = 35.sp)


            Spacer(modifier = Modifier.height(20.dp))


            Button(onClick = {
                testingViewModel.updateList(1)

            }) {
                Text(text = "Update Value", color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))


            Button(onClick = {
                val iIntent = Intent(context, TestingScreenTwo::class.java)
                iIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(iIntent)

            }) {
                Text(text = "Next Screen", color = Color.White)
            }
        }

    }

}


@Composable
fun MainScreens(navController: NavHostController, context: Context) {
    NavHost(navController = navController, startDestination = BottomNavItems.Features.route) {
        composable(BottomNavItems.Features.route) { Features(navController, context = context) }
        composable(BottomNavItems.Home.route) { HomeScreen() }
        composable(BottomNavItems.Search.route) { SearchScreen() }
        composable(BottomNavItems.Profile.route) { ProfileScreen() }

        composable(
            "${NavScreens.ScreenOne.screenName}/{text}",
            arguments = listOf(navArgument("text") {
                type = NavType.StringType
            })
        ) {
            val argText = it.arguments?.getString("text")
            if (argText != null) {
                ScreenOne(navController, argText)
            }
        }

    }
}

@Composable
fun TopAppBar(drawerState: DrawerState, coroutineScope: CoroutineScope) {
    TopAppBar(
        title = {
            Text(text = "Compose Learning")
        },
        backgroundColor = Color.Black,
        contentColor = Color.White,
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch {
                    drawerState.open()
                }
            }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Search, contentDescription = "search")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Notifications, contentDescription = "notif")
            }
        },
    )
}

@Composable
fun DrawerContent(coroutineScope: CoroutineScope, drawerState: DrawerState, navController: NavHostController) {
    ModalDrawerSheet(
        modifier = Modifier
            .fillMaxWidth(.6f)
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.Magenta)
                .fillMaxWidth()
                .height(150.dp)
        ) { Text(text = "") }
        Divider()
        val items = listOf(
            BottomNavItems.Home,
            BottomNavItems.Search,
            BottomNavItems.Profile
        )

        items.forEach { item ->
            NavigationDrawerItem(
                label = { Text(text = item.title, color = Color.Black) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                selected = false,
                onClick = {
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }


                })
        }

    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItems.Features,
        BottomNavItems.Home,
        BottomNavItems.Search,
        BottomNavItems.Profile
    )

    BottomAppBar(
        containerColor = Color.Magenta
    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(.5f),
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }


                }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(showBottomSheet: MutableState<Boolean>, sheetState: SheetState) {
    ModalBottomSheet(
        onDismissRequest = { showBottomSheet.value = false },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            BottomSheetItem(icon = Icons.Default.Star, title = "Add a Story") {
                showBottomSheet.value = false
            }
        }
    }
}

@Composable
fun BottomSheetItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(icon, contentDescription = null, tint = Color.Magenta)
        Text(text = title, color = Color.Magenta, fontSize = 22.sp)
    }
}

