package com.ola.notesapp.data


import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a single note stored in the local SQLite database.
 * Room automatically generates the table from this data class.
 */
@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,            // Unique ID for each note
    val title: String,           // Note title
    val content: String,         // Note body text
    val createdAt: Long          // Timestamp when note was created
)
