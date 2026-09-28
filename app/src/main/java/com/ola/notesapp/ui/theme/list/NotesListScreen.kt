package com.ola.notesapp.ui.theme.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ola.notesapp.ui.theme.list.NoteCard
import com.ola.notesapp.ui.theme.list.NotesListViewModel


/**
 * Main screen showing all notes.
 */


/**
 * Main screen showing all notes.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListScreen(
    viewModel: NotesListViewModel,
    onAddClick: () -> Unit,
    onNoteClick: (Long) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Notes") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->
        if (state.notes.isEmpty()) {
            EmptyNotesState(modifier = Modifier.padding(padding))
        } else {
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(state.notes) { note ->
                    NoteCard(
                        note = note,
                        onClick = { onNoteClick(note.id) },
                        onDeleteClick = { viewModel.deleteNote(note) }
                    )
                }
            }
        }
    }
}


/**
 * Simple empty-state UI shown when no notes exist.
 */
@Composable
private fun EmptyNotesState(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.padding(32.dp),
        tonalElevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text("No notes yet", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            Text("Tap the + button to create your first note.")
        }
    }
}
