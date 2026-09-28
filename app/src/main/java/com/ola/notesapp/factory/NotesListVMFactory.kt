package com.ola.notesapp.factory



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ola.notesapp.domain.NoteRepository
import com.ola.notesapp.ui.theme.editor.NoteEditorViewModel
import com.ola.notesapp.ui.theme.list.NotesListViewModel

/**
 * Factory for creating NotesListViewModel instances.
 *
 * Android's default ViewModelProvider cannot pass constructor parameters,
 * so I have to create a custom factory that injects the NoteRepository.
 *
 * This ensures the ViewModel receives the repository it needs
 * to load, observe, and delete notes from the Room database.
 */
class NotesListVMFactory(
    private val repo: NoteRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Create the NotesListViewModel with the injected repository
        return NotesListViewModel(repo) as T
    }
}

/**
 * Factory for creating NoteEditorViewModel instances.
 *
 * The editor ViewModel also requires the NoteRepository so it can:
 * - load an existing note
 * - save a new note
 * - update an existing note
 *
 * This factory ensures dependency injection is handled cleanly.
 */
class NoteEditorVMFactory(
    private val repo: NoteRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Create the NoteEditorViewModel with the injected repository
        return NoteEditorViewModel(repo) as T
    }
}
