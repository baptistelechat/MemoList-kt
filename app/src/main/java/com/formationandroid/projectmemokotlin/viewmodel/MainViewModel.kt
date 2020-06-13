package com.formationandroid.projectmemokotlin.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.formationandroid.projectmemokotlin.notes.pojos.NoteDTO
import com.formationandroid.projectmemokotlin.repository.MainRepository

class MainViewModel : ViewModel() {

    private lateinit var mainRepository: MainRepository

    fun init(mainRepository: MainRepository) {
        this.mainRepository = mainRepository
    }

    fun getLiveDataMemo(): List<NoteDTO>? {
        return mainRepository.getLiveDataMemo()
    }

    fun insertNote(note: NoteDTO) {
        mainRepository.insertNote(note)
    }
}