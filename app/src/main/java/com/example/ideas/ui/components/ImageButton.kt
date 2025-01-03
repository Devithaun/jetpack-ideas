package com.example.ideas.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ImageButton(
    modifier: Modifier,
    imageVector: ImageVector? = null,
    imageRes: Int? = null,
    contentDescription: String,
    size: Dp = 48.dp,
    onClick: () -> Unit
) {
    when {
        imageVector != null -> {
            Image(
                imageVector = imageVector,
                contentDescription = contentDescription,
                modifier = modifier
                    .size(size)
                    .clickable { onClick() }
            )
        }

        imageRes != null -> {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = contentDescription,
                modifier = modifier
                    .size(size)
                    .clickable { onClick() }
            )
        }
    }
}