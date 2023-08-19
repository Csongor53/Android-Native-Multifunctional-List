package com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey val id: Int? = null,
    val parentId: Int,
    val content: String,
)

class InvalidNoteException(message: String) : Exception(message)