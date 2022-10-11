package net.net76.sunian314.composetv.compose

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.net76.sunian314.composetv.compose.modifiers.dpadFocusable

@Composable
fun HeaderPanel(headers: List<String>, selectedIndex: MutableState<Int>) {
    LazyColumn {
        items(count = headers.size) { i ->
            val header = headers[i]
            Text(
                text = header,
                style = TextStyle(color = Color.White, fontSize = 16.sp),
                modifier = Modifier
                    .dpadFocusable(
                        onFocus = {
                            Log.e("James", "focus $header")
                            selectedIndex.value = i
                        },
                        onClick = { Log.e("James", "click $header") },
                        modifier = { this.headerFocusModifier(it) },
                        focusByDefault = i == 0
                    )
                    .padding(horizontal = 32.dp, vertical = 16.dp)
            )
        }
    }
}

@Composable
private fun Modifier.headerFocusModifier(isFocused: Boolean): Modifier = this
    .alpha(animateFloatAsState(if (isFocused) 1f else 0.5f).value)
    .scale(animateFloatAsState(if (isFocused) 1.2f else 1f).value)
