package com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.docs

import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Document

sealed class DocsEvent {
    object CreateDoc : DocsEvent()
    data class DeleteDoc(val doc: Document) : DocsEvent()
}
