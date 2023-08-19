package com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
data class Document(
    @PrimaryKey val id: Int? = null,
    var title: String,
    val quote: String,
    val timestamp: Long,
    /*@field:TypeConverters(Converters::class)
    val notes: List<Note> = emptyList(),
    var notesIdCount: Int = 0*/
)

/*class Converters {
    @TypeConverter
    fun fromJson(json: String): Note {
        return Gson().fromJson(json, Note::class.java)
    }

    @TypeConverter
    fun toJson(note: Note): String {
        return Gson().toJson(note)
    }

    @TypeConverter
    fun fromList(json: String): List<Note> {
        return Gson().fromJson(json, object : TypeToken<List<Note>>() {}.type)
    }

    @TypeConverter
    fun toList(notes: List<Note>): String {
        return Gson().toJson(notes)
    }
}*/


/*@Entity
data class Document(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val quote: String,
    val timestamp: Long,
    @field:TypeConverters(Converters::class)
    val notes: List<Note>? = emptyList(),
    val notesIdCount: Int = 0
)

class Converters {
    @TypeConverter
    fun fromJson(json: String): Note {
        return Gson().fromJson(json, Note::class.java)
    }

    @TypeConverter
    fun toJson(note: Note): String {
        return Gson().toJson(note)
    }

    @TypeConverter
    fun fromList(json: String): List<Note> {
        return Gson().fromJson(json, object : TypeToken<List<Note>>() {}.type)
    }

    @TypeConverter
    fun toList(notes: List<Note>): String {
        return Gson().toJson(notes)
    }
}*/

/*@Entity
data class Document(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val quote: String,
    val timestamp: Long,
    @TypeConverters(NoteListConverter::class)
    @TypeConverters(NoteConverter::class)
    val notes: List<Note>,
)

class NoteConverter {
    @TypeConverter
    fun fromJson(json: String): Note {
        return Gson().fromJson(json, Note::class.java)
    }

    @TypeConverter
    fun toJson(note: Note): String {
        return Gson().toJson(note)
    }
}

class NoteListConverter {
    @TypeConverter
    fun fromList(list: List<Note>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun toList(jsonString: String): List<Note> {
        val gson = Gson()
        val listType = object : TypeToken<List<Note>>() {}.type
        return gson.fromJson(jsonString, listType)
    }
}*/

class InvalidDocException(message: String) : Exception(message)