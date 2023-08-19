package com.example.mobilecodingfinalcc211049janosi.feature_main.data.data_source

import androidx.room.*
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Document
import kotlinx.coroutines.flow.Flow

// interfaces are abstract(can't be instantiated as an object) methods that can't store states
@Dao
interface DocsDao {

    @Query("SELECT * FROM document")
    fun getAllDocs(): Flow<List<Document>>

    @Query("SELECT * FROM document WHERE id = :id")
    suspend fun getDocById(id: Int): Document?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createDco(doc: Document)

    @Delete
    suspend fun deleteDoc(doc: Document)

    @Query("UPDATE document SET title = :newTitle WHERE id = :id")
    suspend fun updateDoc(newTitle:String, id:Int)
}