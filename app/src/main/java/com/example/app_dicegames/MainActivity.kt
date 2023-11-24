package com.example.app_dicegames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.app_dicegames.ui.theme.App_DiceGamesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App_DiceGamesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

fun DrawScope.circle(offset: (Float) -> Offset) {
    val radius = Dp(20f).value
    drawCircle(
        Color.Black,
        radius = radius,
        center = offset(radius)
    )
}

fun DrawScope.center(){
    circle {
        Offset((size.width / 2f) + (it / 2f), (size.height / 2f) + (it / 2f))
    }
}
fun DrawScope.topRight() {
    circle {
        Offset(size.width - it, it * 2)
    }
}
fun DrawScope.topRight() {
    circle {
        Offset(it * 2f, it * 2f)
    }
}
fun DrawScope.bottonLeft() {
    circle {
        Offset(it * 2f, size.height - it)
    }
}
fun DrawScope.bottonRight() {
        circle {
            Offset(it * 2f, size.height - it)
        }
    }

fun DrawScope.bullet(number: Int) {
    when(number) {
        1 -> {
            center()
        }
        2 -> {
            topRight()
            bottonLeft()
        }
        3 -> {
            center()
            topRight()
            bottonLeft()
        }
        4 -> {
            topRight()
            topLeft()
            bottonRight()
            bottonLeft()
        }
    }
}

@Composable
fun Dice(number: Int, modifier: Modifier) {
    Canvas(
         modifier = modifier
          .size(96.dp, 96.dp)
    ) {
    drawRoundRect(
        color = Color.Green,
        size = size,
        cornerRadius = CornerRadius(20f, 20f),
        topLeft = Offset(10f, 10f)
    )

        bullet(number = number)
   }
}

@Composable
fun App() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Dice(4, Modifier.align(Alignment.Center))

        Button(onClick = { }, modifier = Modifier
            .align(Alignment.Center)
            .offset(y = (100).dp)
        ) {
            Text("Jogar")

        }
    }
}
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        App_DiceGamesTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                App()
            }
        }
    }






