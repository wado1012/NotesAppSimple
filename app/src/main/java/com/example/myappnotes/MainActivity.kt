package com.example.myappnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myappnotes.elements.Screen
import com.example.myappnotes.features.create.NoteCreateScreen
import com.example.myappnotes.features.detail.NoteDetailScreen
import com.example.myappnotes.features.home.NoteHomeScreen
import com.example.myappnotes.ui.theme.MyAppNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    //Navcontroller Implementiert in der Activity
                    val navController = rememberNavController()

                    //NavHost zum erstellen der einzelnen Routen
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NoteHomeScreen.route
                    ) {
                        //Route zum Homescreen
                        composable(route = Screen.NoteHomeScreen.route) {
                            NoteHomeScreen(navController = navController)
                        }
                        //Route zum CreateScreen
                        composable(route= Screen.CreateNoteScreen.route){
                            NoteCreateScreen(navController = navController)
                        }
                        //Route zum Detail Screen mit Hilfe von NoteId, die pro Notiz hochgezählt wird
                        composable(
                            route = Screen.DetailNoteScreen.route + "?noteId={noteId}" ,
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            NoteDetailScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

