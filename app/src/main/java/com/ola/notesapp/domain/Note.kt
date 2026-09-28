package com.ola.notesapp.domain
import com.ola.notesapp.data.NoteEntity


/**
 * Domain model used by UI and ViewModels.
 * Keeps UI clean and independent from Room.
 */
data class Note(
    val id: Long = 0,
    val title: String,
    val content: String,
    val createdAt: Long = System.currentTimeMillis()
)

/** Convert Room entity → domain model */
fun NoteEntity.toDomain() = Note(id, title, content, createdAt)

/** Convert domain model → Room entity */
fun Note.toEntity() = NoteEntity(id, title, content, createdAt)
