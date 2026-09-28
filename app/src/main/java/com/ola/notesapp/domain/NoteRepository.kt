package com.ola.notesapp.domain

import com.ola.notesapp.data.NoteDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Repository abstracts away Room so ViewModels never touch the database directly.
 */
class NoteRepository(private val dao: NoteDao) {

    /** Stream of all notes as domain models. */
    fun getNotes(): Flow<List<Note>> =
        dao.getAllNotes().map { list -> list.map { it.toDomain() } }

    /** Get a single note. */
    suspend fun getNote(id: Long): Note? =
        dao.getNoteById(id)?.toDomain()

    /** Save (insert or update) a note. */
    suspend fun save(note: Note) =
        dao.upsert(note.toEntity())

    /** Delete a note. */
    suspend fun delete(note: Note) =
        dao.delete(note.toEntity())
}

