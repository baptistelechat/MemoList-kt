package com.formationandroid.projectmemokotlin.notes.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.formationandroid.projectmemokotlin.notes.pojos.NoteDTO

@Database(entities = [NoteDTO::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDAO(): NoteDAO
}