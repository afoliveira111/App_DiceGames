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
import kotlinx.coroutines.delay
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App_DiceGamesTheme {
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

fun DrawScope.center() {
    circle {
        Offset((size.width / 2f) + (it / 2f), (size.height / 2f) + (it / 2f))
    }
}
fun DrawScope.topRight() {
    circle {
        Offset(size.width - it, it * 2f)
    }
}
fun DrawScope.topLeft() {
    circle {
        Offset(it * 2f, it * 2f)
    }
}
fun DrawScope.bottomLeft() {
    circle {
        Offset(it * 2f, size.height - it)
    }
}
fun DrawScope.bottomRight() {
        circle {
         Offset(size.width - it,size.height - it)
     }
}
fun DrawScope.centerLeft() {
    circle {
        Offset(it * 2f, (size.height / 2f) + (it / 2f))
    }
}
fun DrawScope.centerRight() {
    circle {
        Offset(size.width - it , (size.height / 2f) + (it / 2f))
    }
}

fun DrawScope.bullet(number: Int) {
    when(number) {
        1 -> {
            center()
        }
        2 -> {
            topRight()
            bottomLeft()
        }
        3 -> {
            center()
            topRight()
            bottomLeft()
        }
        4 -> {
            topRight()
            topLeft()
            bottomRight()
            bottomLeft()
        }
        5 -> {
            center()
            topRight()
            topLeft()
            bottomRight()
            bottomLeft()

        }
        6 -> {
            topRight()
            topLeft()
            centerRight()
            centerLeft()
            bottomRight()
            bottomLeft()
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
        color = Color.White,
        size = size,
        cornerRadius = CornerRadius(20f, 20f),
        topLeft = Offset(10f, 10f)
    )

        bullet(number = number)
   }
}

@Composable
fun App() {
    var r by remember { mutableStateOf(3)  }
    var timer by remember { mutableStateOf(0)   }

    LaunchedEffect(key1 = timer) {
        if (timer > 0) {
            delay((500 * (1.0f / timer)).toLong())
            r = kotlin.random.Random.nextInt(1, 7)
            timer -= 1
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {

        Dice(r, Modifier.align(Alignment.Center))

        Button(onClick = {
                timer = 60
        }, modifier = Modifier
            .align(Alignment.Center)
            .offset(y = (120).dp)
        ) {
            if (timer > 0) {
                 Text(text = "$timer")
            }else {
                Text("Jogar")
            }
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






