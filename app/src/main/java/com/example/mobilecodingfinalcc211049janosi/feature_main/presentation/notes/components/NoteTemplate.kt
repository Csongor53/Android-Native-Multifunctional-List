package com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.notes.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Note

// This composable is a single note that gets reproduced x times the Note element passed to it is an iteration of items composable (list, foreach)
// from NotesScreen

@Composable
fun NoteTemplate(
    note: Note,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = note.content,
                style = MaterialTheme.typography.body1,
                maxLines = 10,
            )
        }
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.RemoveCircle,
                contentDescription = "Delete note",
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}