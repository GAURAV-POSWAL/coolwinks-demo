package com.example.coolwinksapp.di

import com.example.coolwinksapp.ui.activity.UserDetailsActivity
import com.example.coolwinksapp.ui.fragment.CoolMainFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(activity: CoolMainFragment)
    fun inject(activity: UserDetailsActivity)

}