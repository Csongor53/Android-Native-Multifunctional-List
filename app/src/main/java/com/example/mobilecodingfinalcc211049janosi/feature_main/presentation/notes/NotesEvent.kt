package com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.notes

import androidx.compose.ui.focus.FocusState
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Note

sealed class NotesEvent {
    data class EnteredTitle(val value: String): NotesEvent()
    data class ChangeTitleFocus(val focusState: FocusState): NotesEvent()
    data class InputEdit(val input: String): NotesEvent()
    object AddNote: NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object SaveNote: NotesEvent()


    /*data class EnteredContent(val value: String, val note: Note): NotesEvent()
    data class ChangeContentFocus(val focusState: FocusState, val note: Note): NotesEvent()*/
}