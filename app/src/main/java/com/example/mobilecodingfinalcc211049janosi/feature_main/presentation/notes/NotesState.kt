package com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.notes

import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Document
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Note

data class NotesState(
    val document: Document? = null,
    val notes: List<Note> = emptyList(),
    val inputText: String = "",
)
