package com.example.myappnotes.features.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myappnotes.elements.uiElements.AppBottomBarIcons
import com.example.myappnotes.elements.uiElements.TextFieldElement
import com.example.myappnotes.elements.uiElements.TopBarElement
import com.example.myappnotes.features.functionfeatures.NoteEventInProcess
import kotlinx.coroutines.flow.collectLatest

@ExperimentalComposeUiApi
@Composable
fun NoteDetailScreen(
    navController: NavHostController,
    viewModel: NoteDetailViewModel = hiltViewModel()
){

    val scaffoldState = rememberScaffoldState()

    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value
    val localFocusManager = LocalFocusManager.current
    val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is NoteDetailViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is NoteDetailViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
                is NoteDetailViewModel.UiEvent.DeleteNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        topBar = { TopBarElement() },
        bottomBar = { AppBottomBarIcons(RoundedCornerShape(50),
            onDeleteClick = {viewModel.onEvent(NoteEventInProcess.DeleteNote)})},
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(NoteEventInProcess.SaveNote)
                },
                shape = RoundedCornerShape(50),
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.secondary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save note")
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            TextFieldElement(
                text = titleState.text,
                replace = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(NoteEventInProcess.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(NoteEventInProcess.ChangeTitleFocus(it))
                },
                replaceTextShown = contentState.isHintVisible,
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onNext = { localFocusManager.moveFocus(FocusDirection.Down) }
                ),
                imeAction = ImeAction.Next,
                textStyle = MaterialTheme.typography.h5,
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldElement(
                text = contentState.text,
                replace = contentState.hint,
                onValueChange = {
                    viewModel.onEvent(NoteEventInProcess.EnteredContent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(NoteEventInProcess.ChangeContentFocus(it))
                },
                replaceTextShown = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                keyboardActions = KeyboardActions(
                    onDone = {
                        localFocusManager.clearFocus()
                        localSoftwareKeyboardController?.hide()
                    }
                ),
                imeAction = ImeAction.Done,
                modifier = Modifier.fillMaxHeight()
            )
            Box(modifier = Modifier.background(MaterialTheme.colors.surface)
                .height(60.dp)) {
            }
        }

    }
}