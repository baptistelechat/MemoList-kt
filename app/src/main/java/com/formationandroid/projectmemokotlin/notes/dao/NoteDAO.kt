package com.formationandroid.projectmemokotlin.notes.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.formationandroid.projectmemokotlin.notes.pojos.NoteDTO

@Dao
abstract class NoteDAO {
    @Query("SELECT * FROM notes")
    abstract fun getListeNotes(): MutableList<NoteDTO>

    @Query("SELECT COUNT(*) FROM notes WHERE intitule = :intitule")
    abstract fun countNotesByIntitule(intitule: String?): Long

    @Insert
    abstract fun insert(vararg notes: NoteDTO?)

    @Update
    abstract fun update(vararg notes: NoteDTO?)

    @Delete
    abstract fun delete(vararg notes: NoteDTO?)
}