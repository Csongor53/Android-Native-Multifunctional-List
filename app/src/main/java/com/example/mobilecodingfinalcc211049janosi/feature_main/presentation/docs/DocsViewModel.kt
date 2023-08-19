package com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.docs

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.model.Document
import com.example.mobilecodingfinalcc211049janosi.feature_main.domain.use_case.docs.DocsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocsViewModel @Inject constructor(
    private val docsUseCases: DocsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(DocsState())
    val state: State<DocsState> = _state

    private var getDocsJob: Job? = null

    init {
        getDocs()
    }

    fun onEvent(event: DocsEvent) {
        when (event) {
            is DocsEvent.CreateDoc -> {
                viewModelScope.launch {
                    docsUseCases.createDocUseCase(
                        Document(
                            title = "New Document",
                            quote = "Try editing your Document!",
                            timestamp = System.currentTimeMillis(),
                        )
                    )
                }
            }
            is DocsEvent.DeleteDoc -> {
                viewModelScope.launch {
                    docsUseCases.deleteDocUseCase(event.doc)
                    // Recently deleted can be added later
                }
            }
        }
    }

    private fun getDocs() {
        getDocsJob?.cancel()
        getDocsJob = docsUseCases.getAllDocs().onEach { allDocs ->
            _state.value = state.value.copy(
                docs = allDocs,
            )
        }.launchIn(viewModelScope)
    }
}