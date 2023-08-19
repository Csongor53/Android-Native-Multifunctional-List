package com.example.mobilecodingfinalcc211049janosi.feature_main.domain.use_case.notes

import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Note
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.repository.NotesRepository

class AddNoteUseCase(
    private val repo: NotesRepository
) {
    suspend operator fun invoke(note: Note) {
        return repo.insertNote(note)
    }
}