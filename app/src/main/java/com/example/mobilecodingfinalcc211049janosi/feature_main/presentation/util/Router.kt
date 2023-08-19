package com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.util

sealed class Route(val route: String) {
    object DocsScreen: Route("docs_route")
    object NoteScreen: Route("note_route")
}
