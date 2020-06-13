package com.formationandroid.projectmemokotlin.repository

import com.formationandroid.projectmemokotlin.di.DIApplication
import com.formationandroid.projectmemokotlin.notes.dao.NoteDAO
import com.formationandroid.projectmemokotlin.notes.pojos.NoteDTO
import javax.inject.Inject

class MainRepository {
    @Inject lateinit var noteDAO: NoteDAO

    fun getLiveDataMemo(): List<NoteDTO> {
        return noteDAO.getListeNotes()
    }

    fun insertNote(memo: NoteDTO) {
        noteDAO.insert(memo)
    }

    fun deleteNote(memo: NoteDTO) {
        noteDAO.delete(memo)
    }

    init {
        DIApplication.getAppComponent()?.inject(this)
    }
}