package com.example.mobilecodingfinalcc211049janosi.feature_main.domain.repository

import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun getNotes(parentId: Int): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}