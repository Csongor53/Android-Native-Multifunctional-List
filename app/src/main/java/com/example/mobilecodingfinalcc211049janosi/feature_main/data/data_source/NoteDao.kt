package com.example.mobilecodingfinalcc211049janosi.feature_main.data.data_source

import androidx.room.*
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Document
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Note
import kotlinx.coroutines.flow.Flow

// interfaces are abstract(can't be instantiated as an object) methods that can't store states
@Dao
interface NoteDao {

    @Query("SELECT * FROM note WHERE parentId = :id")
    fun getNotes(id: Int): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Insert(/*onConflict = OnConflictStrategy.REPLACE*/)
    suspend fun addNewNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}