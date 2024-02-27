package com.task.game

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TikTak()
        }
    }
}
data class TikTakdata(
    val buttonText: String = "ClickMe",
    var isChecked: MutableState<Boolean?> = mutableStateOf(null)
)
@Preview
@Composable
fun TikTak() {

    val context = LocalContext.current
    var valuesetter by remember { mutableStateOf(false) }
    var gameWon by remember { mutableStateOf(false) }
    var winner by remember { mutableStateOf(false) }
    val listTikTak = remember {
        mutableStateListOf(
            TikTakdata(),
            TikTakdata(),
            TikTakdata(),
            TikTakdata(),
            TikTakdata(),
            TikTakdata(),
            TikTakdata(),
            TikTakdata(),
            TikTakdata(),
        )
    }

    fun checkWinner(): Boolean {
        val winningConditions = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )
        for (condition in winningConditions) {
            val values = condition.map { listTikTak[it].isChecked.value }
            if (values.distinct().size == 1 && values[0] != null) {
                winner = values[0]!!
                return true
            }
        }

        return false
    }

    fun emptycase(): Boolean {
        if (listTikTak.all { it.isChecked.value != null }) {
            gameWon = true
            val message = "Match draw"
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyVerticalGrid(
            userScrollEnabled = false,
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 25.dp, end = 25.dp, bottom = 10.dp),
            columns = GridCells.Fixed(3)
        ) {
            itemsIndexed(listTikTak) { index, data ->
                Button(
                    modifier = Modifier
                        .height(100.dp)
                        .width(40.dp)
                        .padding(1.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        data.isChecked.value = valuesetter
                        valuesetter = !valuesetter
                        emptycase()
                        if (checkWinner()) {
                            val won=R.drawable.ic_launcher_background
                            val won2=Icons.Default.Done
                            gameWon = true
                            val message = if (winner) ("Player close icon win  wins!") else ("Player done icon wins!")
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    },
                    enabled = !gameWon && data.isChecked.value == null
                ) {
                    data.isChecked.value?.let {
                        Icon(
                            imageVector = if (it) Icons.Default.Close else Icons.Default.Done,
                            contentDescription = null,
                            tint = Color.Red
                        )
                    } ?: Text(
                        text = "Click me",
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
            }
        }
        if (gameWon) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                    listTikTak.forEach { it.isChecked.value = null }
                    gameWon = false
                }
            ) {
                Text(text = "Reset Game")
            }
        }
    }
}