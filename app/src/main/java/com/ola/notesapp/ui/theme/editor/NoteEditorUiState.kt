package com.ola.notesapp.ui.theme.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ola.notesapp.domain.Note
import com.ola.notesapp.domain.NoteRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * UI state for the Note Editor screen.
 * Holds the current note being edited or created.
 */
data class NoteEditorUiState(
    val id: Long = 0,
    val title: String = "",
    val content: String = "",
    val isNew: Boolean = true
)

/**
 * ViewModel for creating or editing notes.
 *
 * Responsibilities:
 * - Load an existing note
 * - Update title/content fields
 * - Save new or updated notes
 */
class NoteEditorViewModel(
    private val repo: NoteRepository
) : ViewModel() {

    /** Internal mutable state */
    private val _uiState = MutableStateFlow(NoteEditorUiState())

    /** Public read-only state for the UI */
    val uiState: StateFlow<NoteEditorUiState> = _uiState

    /**
     * Loads an existing note from the database.
     * Called when navigating to the editor with a note ID.
     */
    fun loadNote(id: Long) {
        viewModelScope.launch {
            repo.getNote(id)?.let { note ->
                _uiState.value = NoteEditorUiState(
                    id = note.id,
                    title = note.title,
                    content = note.content,
                    isNew = false
                )
            }
        }
    }

    /** Resets state for creating a new note */
    fun resetForNew() {
        _uiState.value = NoteEditorUiState()
    }

    /** Updates the title field */
    fun onTitleChange(new: String) {
        _uiState.update { it.copy(title = new) }
    }

    /** Updates the content field */
    fun onContentChange(new: String) {
        _uiState.update { it.copy(content = new) }
    }

    /**
     * Saves the note to the database.
     * Calls onSaved() when finished so the UI can navigate back.
     */
    fun save(onSaved: () -> Unit) {
        viewModelScope.launch {
            val state = _uiState.value

            val note = Note(
                id = state.id,
                title = state.title.ifBlank { "Untitled" },
                content = state.content
            )

            repo.save(note)
            onSaved()
        }
    }
}