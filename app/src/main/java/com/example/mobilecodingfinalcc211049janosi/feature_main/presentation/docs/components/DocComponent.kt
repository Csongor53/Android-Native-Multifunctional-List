package com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.docs.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Document

@Composable
fun DocComponent(
    doc: Document, modifier: Modifier = Modifier, onDeleteClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .drawBehind {
                val strokeWidth = 2 * density
                val y = size.height - strokeWidth / 2

                drawLine(
                    Color.LightGray,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            },
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp)
                .padding(bottom = 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = doc.title,
                style = MaterialTheme.typography.h5,
                maxLines = 1,
            )
            Box(modifier = Modifier.width(150.dp)) {
                Text(
                    text = doc.quote,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Row(
            modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp)
                .padding(bottom = 0.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = onDeleteClick,
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete document",
                )
            }
        }
    }
}

