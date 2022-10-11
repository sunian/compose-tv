package net.net76.sunian314.composetv.compose.modifiers

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.InputMode
import androidx.compose.ui.platform.LocalInputModeManager

fun Modifier.dpadFocusable(
    onFocus: () -> Unit = {},
    onClick: () -> Unit = {},
    modifier: @Composable Modifier.(Boolean) -> Modifier,
    indication: Indication? = null,
    focusByDefault: Boolean = false
) = composed {
    val boxInteractionSource = remember { MutableInteractionSource() }
    val isItemFocused by boxInteractionSource.collectIsFocusedAsState()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(focusRequester) {
        if (focusByDefault) {
            focusRequester.requestFocus()
        }
    }
    this
        .modifier(isItemFocused)
        .onFocusChanged { focusState ->
            if (focusState.isFocused) {
                onFocus()
            }
        }
        .focusRequester(focusRequester)
        .focusable(interactionSource = boxInteractionSource)
        .clickable(
            interactionSource = boxInteractionSource,
            indication = indication,
            onClick = {
                if (!isItemFocused) {
                    focusRequester.requestFocus()
                }
                onClick()
            }
        )
}