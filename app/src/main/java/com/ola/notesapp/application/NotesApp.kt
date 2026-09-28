package com.ola.notesapp.application

import android.app.Application
import com.ola.notesapp.data.NoteDatabase
import com.ola.notesapp.domain.NoteRepository

/**
 * Application class used to provide dependencies.
 */
class NotesApp : Application() {

    val database by lazy { NoteDatabase.getInstance(this) }
    val repository by lazy { NoteRepository(database.noteDao()) }
}
