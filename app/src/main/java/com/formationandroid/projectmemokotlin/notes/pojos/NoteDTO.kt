package com.formationandroid.projectmemokotlin.notes.pojos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class NoteDTO(var intitule: String?, var description: String?) {
    @PrimaryKey(autoGenerate = true)
    var memoId: Long = 0
}