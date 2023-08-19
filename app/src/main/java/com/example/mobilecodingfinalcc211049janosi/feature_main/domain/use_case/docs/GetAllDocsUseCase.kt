package com.example.mobilecodingfinalcc211049janosi.feature_main.domain.use_case.docs

import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Document
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.repository.DocsRepository
import kotlinx.coroutines.flow.Flow

class GetAllDocsUseCase(
    private val repo: DocsRepository
) {
    operator fun invoke(): Flow<List<Document>> {
        return repo.getAllDocs()
    }
}