package com.example.mobilecodingfinalcc211049janosi.feature_main.domain.use_case.notes

import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Note
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(
    private val repo: NotesRepository
) {
    operator fun invoke(parentId: Int): Flow<List<Note>> {
        return repo.getNotes(parentId)
    }
}