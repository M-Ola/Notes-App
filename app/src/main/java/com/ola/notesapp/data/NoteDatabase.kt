package com.ola.notesapp.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * The Room database that stores notes.
 * Uses a singleton pattern to ensure only one instance exists.
 */
@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        /** Returns the database instance, creating it if needed. */
        fun getInstance(context: Context): NoteDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "notes.db"   // Local persistent storage file
                ).build().also { INSTANCE = it }
            }
    }
}
