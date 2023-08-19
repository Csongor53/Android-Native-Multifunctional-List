package com.example.mobilecodingfinalcc211049janosi.feature_main.domain.use_case.notes

data class NotesUseCases(
    val getNotes: GetNotesUseCase,
    val getNoteById: GetNoteByIdUseCase,
    val addNote: AddNoteUseCase,
    val deleteNote: DeleteNoteUseCase,
)
