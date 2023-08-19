package com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Document
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Note
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.use_case.docs.DocsUseCases
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.use_case.notes.NotesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val docsUseCases: DocsUseCases,
    private val notesUseCases: NotesUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private val _noteTitle = mutableStateOf(NotesTextState(hint = "Add a title..."))
    val noteTitle: State<NotesTextState> = _noteTitle


    /* private val _notesContent = mutableListOf<MutableState<NotesTextState>>()
     val notesContent: List<State<NotesTextState>> = _notesContent*/

    private var currentDocId: Int = -1

    private var getNotesJob: Job? = null

    init {
        // this line gets a saved state from the rout called navigation argument set as default in MainActivity or added to the rout call
        savedStateHandle.get<Int>("docId")?.let { docId ->
            // note id is passed when opening an existing note, -1 is the default id added to the rout in MainActivity
            // if note exists
            if (docId != -1) {
                viewModelScope.launch {
                    docsUseCases.getDocUseCase(docId)?.also { doc ->
                        currentDocId = doc.id!!
                        // Set Document Title State
                        _noteTitle.value = noteTitle.value.copy(
                            text = doc.title,
                            isHintVisible = false
                        )
                        // Set Parent Document and Notes State
                        _state.value = state.value.copy(
                            document = doc
                        )
                        getNotes(doc.id)


                        println("Document $docId loaded!")
                        /*// Populate _notesContent with notes from the current document
                        doc.notes.let { note ->
                            viewModelScope.launch {
                                _state.value = state.value.copy(
                                    notes = note
                                )
                            }
                        }
                        _notesContent.clear()
                        _notesContent.addAll(doc.notes.map {
                            mutableStateOf(
                                NotesTextState(
                                    text = it.content,
                                    hint = "Write your notes...",
                                )
                            )
                        })*/
                    }
                }
            } else {
                println("docId Read: " + savedStateHandle.get<Int>("docId"))
                throw error(
                    "Document not found! Document number read = ${
                        savedStateHandle.get<Int>(
                            "docId"
                        )
                    }"
                )
            }
        }
    }

    private fun getNotes(parentId: Int) {
        getNotesJob?.cancel()
        getNotesJob = notesUseCases.getNotes(parentId).onEach { notes ->
            _state.value = state.value.copy(
                notes = notes,
            )
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is NotesEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }

            is NotesEvent.InputEdit -> {
                _state.value = state.value.copy(
                    inputText = event.input
                )
            }
            is NotesEvent.AddNote -> {
                if (currentDocId != -1) {
                    viewModelScope.launch {
                        notesUseCases.addNote(
                            Note(
                                parentId = currentDocId,
                                content = state.value.inputText
                            )
                        )
                        _state.value = state.value.copy(
                            inputText = ""
                        )
                    }
                }
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCases.deleteNote(event.note)
                }
            }
            is NotesEvent.SaveNote -> {
                viewModelScope.launch {
                    docsUseCases.updateDocUseCase(noteTitle.value.text,currentDocId)

                }
            }

            /*is NotesEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is NotesEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is NotesEvent.EnteredContent -> {
                _notesContent[event.note.id].value = _notesContent[event.note.id].value.copy(
                    // TODO idk wht this will return
                    text = event.value
                )
            }
            is NotesEvent.ChangeContentFocus -> {
                println("event note id: " + event.note.id)
                println(_notesContent)
                _notesContent[event.note.id].value = _notesContent[event.note.id].value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _notesContent[event.note.id].value.text.isBlank()
                )
            }
            is NotesEvent.AddNote -> {
                _state.value = state.value.copy(
                    notes = state.value.notes +
                            Note(
                                id = (_state.value.document?.notesIdCount
                                    ?: (state.value.notes.size)) + 1,
                                checked = false,
                                content = "",
                            )
                )
                _notesContent.add(
                    mutableStateOf(
                        NotesTextState(
                            text = "",
                            hint = "Write your notes...",
                        )
                    )
                )
                _state.value.document?.notesIdCount?.plus(1)!!
            }
            is NotesEvent.SaveNote -> {

            }*/
        }
    }
}