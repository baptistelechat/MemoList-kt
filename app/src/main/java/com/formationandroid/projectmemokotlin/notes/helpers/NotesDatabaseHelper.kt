package com.formationandroid.projectmemokotlin.notes.helpers

import android.content.Context
import androidx.room.Room
import com.formationandroid.projectmemokotlin.notes.dao.NotesDatabase

class NotesDatabaseHelper(context: Context?) {
    companion object {
        private var dbHelper: NotesDatabaseHelper? = null
        private lateinit var db: NotesDatabase

        @Synchronized
        fun getDatabase(c: Context): NotesDatabase {
            if (dbHelper == null)
                dbHelper =
                    NotesDatabaseHelper(
                        c.applicationContext
                    )

            return db
        }
    }

    init {
        db = context?.let {
            Room
                .databaseBuilder(it, NotesDatabase::class.java, "memos.db")
                .allowMainThreadQueries()
                .build()
        }!!
    }
}