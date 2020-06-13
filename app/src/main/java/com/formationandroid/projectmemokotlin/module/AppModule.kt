package com.formationandroid.projectmemokotlin.module

import android.app.Application
import android.content.Context
import com.formationandroid.projectmemokotlin.notes.dao.NoteDAO
import com.formationandroid.projectmemokotlin.notes.helpers.NotesDatabaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideNoteDAO(applicationContext: Context): NoteDAO {
        return NotesDatabaseHelper.getDatabase(applicationContext).noteDAO()
    }
}