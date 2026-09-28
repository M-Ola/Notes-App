package com.ola.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ola.notesapp.application.NotesApp
import com.ola.notesapp.editor.NoteEditorScreen
import com.ola.notesapp.factory.NoteEditorVMFactory
import com.ola.notesapp.factory.NotesListVMFactory
import com.ola.notesapp.ui.theme.NotesAppTheme
import com.ola.notesapp.ui.theme.editor.NoteEditorViewModel
import com.ola.notesapp.ui.theme.list.NotesListScreen
import com.ola.notesapp.ui.theme.list.NotesListViewModel

/**
 * Main activity hosting the Compose UI and navigation graph.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as NotesApp
        val repo = app.repository

        setContent {
            NotesAppTheme {
                val navController = rememberNavController()

                val listVM: NotesListViewModel = viewModel(factory = NotesListVMFactory(repo))
                val editorVM: NoteEditorViewModel = viewModel(factory = NoteEditorVMFactory(repo))

                NavHost(navController, startDestination = "list") {

                    composable("list") {
                        NotesListScreen(
                            viewModel = listVM,
                            onAddClick = {
                                editorVM.resetForNew()
                                navController.navigate("editor")
                            },
                            onNoteClick = { id ->
                                navController.navigate("editor/$id")
                            }
                        )
                    }

                    composable("editor") {
                        NoteEditorScreen(
                            viewModel = editorVM,
                            onSaved = { navController.popBackStack() },
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }

                    composable(
                        "editor/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.LongType })
                    ) { backStack ->
                        val id = backStack.arguments?.getLong("id") ?: 0L
                        editorVM.loadNote(id)

                        NoteEditorScreen(
                            viewModel = editorVM,
                            onSaved = { navController.popBackStack() },
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}