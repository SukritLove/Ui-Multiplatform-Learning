package model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

object Model {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    fun TopBar(title: String, modifier: Modifier = Modifier, onBackClick: (() -> Unit)? = null) {
        TopAppBar(modifier = modifier, contentPadding = PaddingValues(0.dp)) {

            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (onBackClick != null) {
                    IconButton(onClick = onBackClick, Modifier.weight(1f)) {
                        Icon(
                            painter = painterResource("drawable/left.png"),
                            null,
                            Modifier.size(30.dp),
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
                Text(
                    text = title,
                    style = TextStyle(fontSize = 20.sp),
                    modifier = Modifier.weight(3f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }

    }




}