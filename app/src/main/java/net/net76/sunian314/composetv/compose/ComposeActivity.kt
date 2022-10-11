package net.net76.sunian314.composetv.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.net76.sunian314.composetv.models.MovieList

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val selectedHeader = remember { mutableStateOf(0) }
            Row {
                HeaderPanel(
                    headers = MovieList.MOVIE_CATEGORY.toList(),
                    selectedIndex = selectedHeader
                )
                Text(
                    text = "${selectedHeader.value}",
                    style = TextStyle(fontSize = 24.sp, color = Color.White),
                    modifier = Modifier.padding(24.dp),
                )
            }
        }
    }
}