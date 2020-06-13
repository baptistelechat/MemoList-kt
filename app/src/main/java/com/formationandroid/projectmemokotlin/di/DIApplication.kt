package com.formationandroid.projectmemokotlin.di

import android.app.Application

class DIApplication : Application() {
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().application(this).build()
    }

    companion object {
        private lateinit var instance: DIApplication
        fun getAppComponent(): AppComponent? {
            return instance.appComponent
        }
    }
}