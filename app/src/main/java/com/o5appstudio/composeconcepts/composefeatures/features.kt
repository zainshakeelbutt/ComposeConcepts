package com.o5appstudio.composeconcepts.composefeatures

import android.media.Image
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest



@Composable
fun TextFieldCustom(
    label: String,
    hint: String,
    textStateValue: MutableState<String>,
    keyboardType: KeyboardType,
    isPassword: Boolean
) {
    val isFocused = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = textStateValue.value,
        onValueChange = { textStateValue.value = it },
        label = { Text(text = label) },
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedContainerColor = Color.LightGray,
            unfocusedContainerColor = Color.Cyan,
            focusedLeadingIconColor = Color.Black,
            unfocusedLeadingIconColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        textStyle = TextStyle(

        ),
        placeholder = {
            Text(text = hint)
        },
        leadingIcon = {
            Icon(Icons.Outlined.Email, contentDescription = "")
        },
        trailingIcon = {
            IconButton(onClick = {
                textStateValue.value = ""
            }) {
                Icon(Icons.Outlined.Clear, contentDescription = "")
            }
        },

        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(
            onSend = {
                focusManager.clearFocus()
                keyboardController?.hide()
            },
            onDone = {
                focusManager.clearFocus()
                keyboardController?.hide()
            }
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(8.dp),
                color = if (isFocused.value) Color.Magenta else Color.White
            )
            .onFocusChanged {
                isFocused.value = it.isFocused
            }

    )
}

@Composable
fun HeightUI(dp: Dp) {
    Spacer(modifier = Modifier.height(dp))
}
@Composable
fun WidthUi(dp: Dp) {
    Spacer(modifier = Modifier.width(dp))
}

@Composable
fun LoadNetworkImage(imageUrl : String, imageSize : Dp, corners :Dp) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
            .error(android.R.drawable.stat_notify_error)
            .build()
    )
    Image(
        painter = painter,
        contentDescription = "Network Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(imageSize)
            .clip(RoundedCornerShape(corners))

    )
}

@Composable
fun ButtonCustom(text: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color.Magenta
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 10.dp)

    ) {
        Text(text = text)
    }
}

@Composable
fun SwitchOp() {

    val isChecked = remember {
        mutableStateOf(true)
    }

    Switch(
        checked = isChecked.value,
        onCheckedChange = {
            isChecked.value = it
        }
    )

    if (isChecked.value) {
        Log.d("O5Compose", "Switch is ON")
    } else {
        Log.d("O5Compose", "Switch is Off")
    }


}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreen() {
    val userName = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    Column {
        val context = LocalContext.current
        TextFieldCustom("Email", "Enter Email", userName, KeyboardType.Email, false)
        HeightUI(dp = 5.dp)
        TextFieldCustom("Password", "Enter Password", password, KeyboardType.Password, true)
        HeightUI(dp = 10.dp)
        ButtonCustom(text = "Login") {
            if (userName.value.isEmpty() || password.value.isEmpty()) {
                Toast.makeText(context, "Fill All Fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            }

        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)

data class ImageList(val image: String, val description: String)

fun dummyData() : List<ImageList>{
    return listOf(
        ImageList(image = "https://4.bp.blogspot.com/-8oh4q_BoZr4/VonezAKIGOI/AAAAAAAAAUw/gAFtiXO3B-w/s1600-r/edited%2Bdp.jpg", description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words"),
        ImageList(image = "https://4.bp.blogspot.com/-8oh4q_BoZr4/VonezAKIGOI/AAAAAAAAAUw/gAFtiXO3B-w/s1600-r/edited%2Bdp.jpg", description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words"),
        ImageList(image = "https://4.bp.blogspot.com/-8oh4q_BoZr4/VonezAKIGOI/AAAAAAAAAUw/gAFtiXO3B-w/s1600-r/edited%2Bdp.jpg", description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words"),
        ImageList(image = "https://4.bp.blogspot.com/-8oh4q_BoZr4/VonezAKIGOI/AAAAAAAAAUw/gAFtiXO3B-w/s1600-r/edited%2Bdp.jpg", description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words"),
        ImageList(image = "https://4.bp.blogspot.com/-8oh4q_BoZr4/VonezAKIGOI/AAAAAAAAAUw/gAFtiXO3B-w/s1600-r/edited%2Bdp.jpg", description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words"),
        ImageList(image = "https://4.bp.blogspot.com/-8oh4q_BoZr4/VonezAKIGOI/AAAAAAAAAUw/gAFtiXO3B-w/s1600-r/edited%2Bdp.jpg", description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words"),
        ImageList(image = "https://4.bp.blogspot.com/-8oh4q_BoZr4/VonezAKIGOI/AAAAAAAAAUw/gAFtiXO3B-w/s1600-r/edited%2Bdp.jpg", description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words"),
        ImageList(image = "https://4.bp.blogspot.com/-8oh4q_BoZr4/VonezAKIGOI/AAAAAAAAAUw/gAFtiXO3B-w/s1600-r/edited%2Bdp.jpg", description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words"),
        ImageList(image = "https://4.bp.blogspot.com/-8oh4q_BoZr4/VonezAKIGOI/AAAAAAAAAUw/gAFtiXO3B-w/s1600-r/edited%2Bdp.jpg", description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words"),
        ImageList(image = "https://4.bp.blogspot.com/-8oh4q_BoZr4/VonezAKIGOI/AAAAAAAAAUw/gAFtiXO3B-w/s1600-r/edited%2Bdp.jpg", description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words"),
    )
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImageRowItem(imageData : ImageList, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(start = 20.dp, end = 20.dp, bottom = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
        ) {
            LoadNetworkImage(imageUrl = imageData.image, imageSize = 80.dp, corners = 0.dp)
            WidthUi(dp = 10.dp)
            Text(
                text = imageData.description,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
    
}

@Composable
fun RecyclerView(imageList: List<ImageList>, onClick: (imageData : ImageList) -> Unit){
    Box (
        modifier = Modifier
            .height(500.dp)
    ){
        LazyColumn(
            userScrollEnabled = false
        ) {
            items(imageList){
                ImageRowItem(imageData = it){
                   onClick(it)
                }
            }
        }
    }

}
