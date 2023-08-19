package com.example.mobilecodingfinalcc211049janosi.feature_main.domain.use_case.docs

import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Document
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.repository.DocsRepository

class DeleteDocUseCase(
    private val repository: DocsRepository
) {
    suspend operator fun invoke(doc: Document) {
        repository.deleteDoc(doc)
    }
}