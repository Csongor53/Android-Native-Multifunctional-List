package com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.docs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.docs.components.DocComponent
import com.example.mobilecodingfinalcc211049janosi.feature_main.presentation.util.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DocsScreen(
    navController: NavController,
    viewModel: DocsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.padding(0.dp),
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Color.LightGray),
                horizontalArrangement = Arrangement.End,
            ) {
                IconButton(
//                    modifier = Modifier
//                        .background(Color.White),
                    onClick = { viewModel.onEvent(DocsEvent.CreateDoc) },
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add document",
                        tint = Color.Black,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
        },

        // Contains the body of the screen e.g.: the list of documents
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {

                Text(
                    text = "Shopping Lists",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight(600),
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(32.dp))

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .padding(8.dp)
                        .padding(top = 16.dp, bottom = 16.dp)
                ) {
                    DocumentList(navController, viewModel, state, scope)
                }
            }
        })

}

@Composable
fun DocumentList(
    navController: NavController,
    viewModel: DocsViewModel,
    state: DocsState,
    scope: CoroutineScope
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        // items composable takes a list and executes foreach. all info regarding notes are stored in state (state.notes = all notes)
        items(state.docs) { doc ->
            DocComponent(
                doc = doc,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        println("Clicked: " + "Doc nr ${doc.id}")
                        navController.navigate(Route.NoteScreen.route + "?docId=${doc.id}")
                    },
                onDeleteClick = {
                    println("delete clicked!")
                    viewModel.onEvent(DocsEvent.DeleteDoc(doc))
                }
            )
        }
    }

}
