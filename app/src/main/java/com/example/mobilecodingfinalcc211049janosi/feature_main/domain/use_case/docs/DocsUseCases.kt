package com.example.mobilecodingfinalcc211049janosi.feature_main.domain.use_case.docs

data class DocsUseCases(
    val getAllDocs: GetAllDocsUseCase,
    val createDocUseCase: CreateDocUseCase,
    val deleteDocUseCase: DeleteDocUseCase,
    val getDocUseCase: GetDocUseCase,
    val updateDocUseCase: UpdateDocUseCase
)