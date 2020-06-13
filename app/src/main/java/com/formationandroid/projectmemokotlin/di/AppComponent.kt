package com.formationandroid.projectmemokotlin.di

import android.app.Application
import com.formationandroid.projectmemokotlin.module.AppModule
import com.formationandroid.projectmemokotlin.repository.MainRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(mainRepository: MainRepository)
}