package com.example.mobilecodingfinalcc211049janosi.feature_main.data.repository

import com.example.mobilecodingfinalcc211049janosi.feature_main.data.data_source.NoteDao
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Note
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(
    private val dao: NoteDao
) : NotesRepository {
    override fun getNotes(parentId: Int): Flow<List<Note>> {
        return dao.getNotes(parentId)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.addNewNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }


}