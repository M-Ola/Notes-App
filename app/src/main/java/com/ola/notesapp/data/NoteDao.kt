package com.ola.notesapp.data


import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * DAO = Data Access Object
 * Defines all database operations for the notes table.
 */
@Dao
interface NoteDao {

    /** Returns all notes sorted by newest first. */
    @Query("SELECT * FROM notes ORDER BY createdAt DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    /** Returns a single note by ID. */
    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: Long): NoteEntity?

    /** Insert or update a note. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(note: NoteEntity)

    /** Delete a note. */
    @Delete
    suspend fun delete(note: NoteEntity)
}
