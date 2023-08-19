package com.example.mobilecodingfinalcc211049janosi.feature_main.domain.repository

import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Document
import kotlinx.coroutines.flow.Flow

interface DocsRepository {

    fun getAllDocs(): Flow<List<Document>>

    suspend fun getDocById(id: Int): Document?

    suspend fun createDoc(doc: Document)

    suspend fun deleteDoc(doc: Document)

    suspend fun updateDoc(title:String, id:Int)
}