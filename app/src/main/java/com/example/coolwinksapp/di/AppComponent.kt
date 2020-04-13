package com.example.coolwinksapp.di

import com.example.coolwinksapp.ui.CWMainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(activity: CWMainActivity)

}