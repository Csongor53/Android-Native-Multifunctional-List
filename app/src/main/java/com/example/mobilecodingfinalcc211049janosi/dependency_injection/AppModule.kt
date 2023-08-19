package com.example.mobilecodingfinalcc211049janosi.dependency_injection

import android.app.Application
import androidx.room.Room
import com.example.mobilecodingfinalcc211049janosi.core.data.data_source.AppDatabase
import com.example.mobilecodingfinalcc211049janosi.feature_main.data.repository.DocsRepositoryImpl
import com.example.mobilecodingfinalcc211049janosi.feature_main.data.repository.NotesRepositoryImpl
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.repository.DocsRepository
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.repository.NotesRepository
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.use_case.*
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.use_case.docs.*
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.use_case.notes.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app, AppDatabase::class.java, AppDatabase.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDocsRepository(db: AppDatabase): DocsRepository {
        return DocsRepositoryImpl(db.docsDao)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: AppDatabase): NotesRepository {
        return NotesRepositoryImpl(db.noteDao)
    }



    @Provides
    @Singleton
    fun provideDocsUseCases(repository: DocsRepository): DocsUseCases {
        return DocsUseCases(
            getAllDocs = GetAllDocsUseCase(repository),
            createDocUseCase = CreateDocUseCase(repository),
            deleteDocUseCase = DeleteDocUseCase(repository),
            getDocUseCase = GetDocUseCase(repository),
            updateDocUseCase = UpdateDocUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NotesRepository): NotesUseCases {
        return NotesUseCases(
            getNotes = GetNotesUseCase(repository),
            getNoteById = GetNoteByIdUseCase(repository),
            addNote = AddNoteUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
        )
    }
}