package com.ola.notesapp.ui.theme.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ola.notesapp.domain.Note
import com.ola.notesapp.domain.NoteRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * UI state for the Notes List screen.
 * Holds the list of notes that the UI will display.
 */
data class NotesListUiState(
    val notes: List<Note> = emptyList()
)

/**
 * ViewModel for the Notes List screen.
 *
 * Responsibilities:
 * - Observe notes from the repository
 * - Expose notes to the UI using StateFlow
 * - Handle note deletion
 */
class NotesListViewModel(
    private val repo: NoteRepository
) : ViewModel() {

    /**
     * StateFlow that emits the current list of notes.
     * The UI collects this to update automatically when data changes.
     */
    val uiState: StateFlow<NotesListUiState> =
        repo.getNotes()
            .map { NotesListUiState(notes = it) } // Convert list into UI state
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                NotesListUiState()
            )

    /**
     * Deletes a note from the database.
     * Runs inside a coroutine because Room operations are suspend functions.
     */
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repo.delete(note)
        }
    }
}
