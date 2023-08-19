package com.example.mobilecodingfinalcc211049janosi.feature_main.data.repository

import com.example.mobilecodingfinalcc211049janosi.feature_main.data.data_source.DocsDao
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Document
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.repository.DocsRepository
import kotlinx.coroutines.flow.Flow

class DocsRepositoryImpl(
    private val dao: DocsDao
) : DocsRepository {
    override fun getAllDocs(): Flow<List<Document>> {
        return dao.getAllDocs()
    }

    override suspend fun getDocById(id: Int): Document? {
        return dao.getDocById(id)
    }

    override suspend fun createDoc(doc: Document) {
        return dao.createDco(doc)
    }

    override suspend fun deleteDoc(doc: Document) {
        return dao.deleteDoc(doc)
    }

    override suspend fun updateDoc(title:String, id:Int) {
        dao.updateDoc(title, id)
    }

}