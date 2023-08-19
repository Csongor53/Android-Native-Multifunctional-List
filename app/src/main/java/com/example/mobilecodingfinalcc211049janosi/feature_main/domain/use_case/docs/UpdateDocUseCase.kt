package com.example.mobilecodingfinalcc211049janosi.feature_main.domain.use_case.docs

import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Document
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.repository.DocsRepository

class UpdateDocUseCase(
    private val repository: DocsRepository
) {

    suspend operator fun invoke(title:String, id:Int){
        repository.updateDoc(title, id)
    }

}