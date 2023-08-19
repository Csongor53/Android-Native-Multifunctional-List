package com.example.mobilecodingfinalcc211049janosi.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobilecodingfinalcc211049janosi.core.ui.theme.MobileCodingFinalcc211049JanosiTheme
import com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.util.Route
import com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.docs.DocsScreen
import com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.notes.NotesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileCodingFinalcc211049JanosiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Route.DocsScreen.route
                    ) {
                        composable(route = Route.DocsScreen.route) {
                            DocsScreen(navController = navController)
                        }
                        composable(
                            route = Route.NoteScreen.route + "?docId={docId}",
                            arguments = listOf(navArgument(
                                name = "docId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            })
                        ) {
                            val noteId = it.arguments?.getInt("docId") ?: -1
                            NotesScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}