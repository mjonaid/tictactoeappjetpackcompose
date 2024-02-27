//package com.task.game
//
//package com.task.game
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.itemsIndexed
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Close
//import androidx.compose.material.icons.filled.Done
//import androidx.compose.material3.Button
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            TikTak()
//        }
//    }
//}
//
//data class TikTakdata(
//    val buttonText: String = "ClickMe",
//    var isChecked: Boolean? = null
//)
//
//@Composable
//fun TikTak() {
//    val listTikTak = remember {
//        mutableStateListOf(
//            TikTakdata(buttonText = "1"),
//            TikTakdata(buttonText = "2"),
//            TikTakdata(buttonText = "3"),
//            TikTakdata(buttonText = "4"),
//            TikTakdata(buttonText = "5"),
//            TikTakdata(buttonText = "6"),
//            TikTakdata(buttonText = "7"),
//            TikTakdata(buttonText = "8"),
//            TikTakdata(buttonText = "9"),
//        )
//    }
//    LazyVerticalGrid(
//        userScrollEnabled = false,
//        modifier = Modifier
//            .wrapContentSize()
//            .padding(start = 25.dp, end = 25.dp, bottom = 10.dp),
//        columns = GridCells.Fixed(3)
//    ) {
//        itemsIndexed(listTikTak) { index, data ->
//            Button(
//                modifier = Modifier
//                    .size(130.dp)
//                    .padding(1.dp),
//                onClick = {
//                    // Toggle the isChecked state
//                    listTikTak[index].isChecked = data.isChecked?.not() ?: true
//
//                },
//                enabled = data.isChecked != false // Enable the button if isChecked is not false
//            ) {
//                data.isChecked?.let {
//                    Icon(
//                        imageVector = if (it) Icons.Default.Close else Icons.Default.Done,
//                        contentDescription = null,
//                        tint = Color.Red
//                    )
//                } ?: Text(
//                    text = "Click me",
//                    textAlign = TextAlign.Center,
//                    color = Color.Black,
//                    fontSize = 16.sp
//                )
//            }
//        }
//    }
//}
