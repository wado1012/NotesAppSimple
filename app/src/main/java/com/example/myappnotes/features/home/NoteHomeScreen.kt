package com.example.myappnotes.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myappnotes.elements.Screen
import com.example.myappnotes.elements.uiElements.AppBottomBar
import com.example.myappnotes.elements.uiElements.NoteView
import com.example.myappnotes.elements.uiElements.OrderSection
import com.example.myappnotes.elements.uiElements.TopBarElement
import kotlinx.coroutines.launch

@Composable
fun NoteHomeScreen(
    navController: NavController,
    viewModel: NoteHomeViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {TopBarElement()},
        bottomBar = { AppBottomBar(RoundedCornerShape(50)) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.CreateNoteScreen.route)
                },
                shape = RoundedCornerShape(50),
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.secondary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        scaffoldState = scaffoldState
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            OrderSection(
                noteOrder = state.noteOrder,
                onOrderChange = {
                viewModel.onEvent(NoteEvent.Order(it))
            } )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(state.notes) { note ->
                        NoteView(
                            note = note,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        Screen.DetailNoteScreen.route +
                                                "?noteId=${note.id}"
                                    )
                                },
                            onDeleteClick = {
                                viewModel.onEvent(NoteEvent.DeleteNote(note))
                                scope.launch {
                                    val result = scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Note deleted",
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        viewModel.onEvent(NoteEvent.RestoreNote)
                                    }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
            Box(modifier = Modifier.background(MaterialTheme.colors.surface)
                .height(60.dp)) {
            }
        }
    }
}