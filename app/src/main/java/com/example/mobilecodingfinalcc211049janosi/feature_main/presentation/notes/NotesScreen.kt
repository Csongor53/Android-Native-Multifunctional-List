package com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.notes.components.NoteTemplate
import com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.notes.components.NoteTitleText


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel(),
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val state = viewModel.state.value

//    var noteText by remember { mutableStateOf("") }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.padding(0.dp),

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(NotesEvent.SaveNote)
                    navController.navigateUp()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save note"
                )
            }
        },

        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .background(color = Color.LightGray),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp),
                    value = state.inputText,
                    onValueChange = { viewModel.onEvent(NotesEvent.InputEdit(it)) },
                    placeholder = { Text("Type your note here...") }
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(NotesEvent.AddNote)
                        keyboardController?.hide()
                    },
                ) {
                    Icon(
                        modifier = Modifier
                            .size(32.dp),
                        imageVector = Icons.Default.Add,
                        tint = Color.Gray,
                        contentDescription = "Add note"
                    )
                }
            }
        },

        // Contains the body of the screen e.g.: the list of documents
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                NoteTitleText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = viewModel.noteTitle.value.text,
                    hint = viewModel.noteTitle.value.hint,
                    onValueChange = {
                        viewModel.onEvent(NotesEvent.EnteredTitle(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(NotesEvent.ChangeTitleFocus(it))
                    },
                    isHintVisible = viewModel.noteTitle.value.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.height(32.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    items(state.notes) { note ->
                        /*NoteComponent(
                            text = note.content,
                            hint = viewModel.notesContent[0].value.hint,
                            onValueChange = {
                                viewModel.onEvent(NotesEvent.EnteredContent(it, note))
                            },
                            onFocusChange = {
                                viewModel.onEvent(NotesEvent.ChangeContentFocus(it, note))
                            },
                        )*/

                        NoteTemplate(
                            note = note,
                            modifier = Modifier.fillMaxWidth(),
                            onDeleteClick = {
                                viewModel.onEvent(NotesEvent.DeleteNote(note))
                            },
                        )
                    }
                }

            }
        })
}