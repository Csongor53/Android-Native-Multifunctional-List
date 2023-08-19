package com.example.mobilecodingfinalcc211049janosi.core.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobilecodingfinalcc211049janosi.feature_main.data.data_source.DocsDao
import com.example.mobilecodingfinalcc211049janosi.feature_main.data.data_source.NoteDao
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Document
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Note


@Database(
    entities = [Document::class, Note::class],
    version = 6,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract val docsDao: DocsDao
    abstract val noteDao: NoteDao

    companion object {
        const val DB_NAME = "note_app_db"
    }
}
