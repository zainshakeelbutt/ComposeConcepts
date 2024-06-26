package com.o5appstudio.composeconcepts.composefeatures.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.o5appstudio.composeconcepts.composefeatures.ButtonCustom
import com.o5appstudio.composeconcepts.composefeatures.HeightUI
import com.o5appstudio.composeconcepts.composefeatures.ImageList
import com.o5appstudio.composeconcepts.composefeatures.ImageRowItem
import com.o5appstudio.composeconcepts.composefeatures.LoadNetworkImage
import com.o5appstudio.composeconcepts.composefeatures.LoginScreen
import com.o5appstudio.composeconcepts.composefeatures.RecyclerView
import com.o5appstudio.composeconcepts.composefeatures.SwitchOp
import com.o5appstudio.composeconcepts.composefeatures.dummyData

@Composable
fun ScreenOne(navHostController: NavHostController, argText:String?) {


    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(color = Color.LightGray)
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()

        ){
            Text(text = "Screen One")
            HeightUI(dp = 30.dp)
            if (argText != null) {
                Text(text = argText, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            }
            HeightUI(dp = 30.dp)
            ButtonCustom(text = "Go Back") {
                navHostController.popBackStack()
            }
        }
    }


}