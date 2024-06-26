package com.o5appstudio.composeconcepts.testing

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import com.o5appstudio.composeconcepts.MainActivity
import com.o5appstudio.composeconcepts.testing.viewmodels.TestingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestingScreenTwo : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestingTwo()
        }
    }
}

@Composable
fun TestingTwo(testingViewModel :  TestingViewModel = hiltViewModel()){
    val context = LocalContext.current.applicationContext
    val counter = testingViewModel.numList.collectAsState()
    val numList = rememberSaveable {
        mutableStateOf(SharedData.numberList)
    }
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)){
        Column {
            Text(text = counter.value.toString(), fontSize = 35.sp)

            Button(onClick = {
                testingViewModel.updateList(1)

            }) {
                Text(text = "Update Value", color = Color.White)
            }

            Button(onClick = {
                val iIntent = Intent(context, MainActivity::class.java)
                iIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(iIntent)
            }) {
                androidx.compose.material.Text(text = "Back Screen", color = Color.White)
            }

        }
    }

}