package com.example.coolwinksapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coolwinksapp.viewmodel.CoolViewModel
import com.example.coolwinksapp.viewmodel.DetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CoolViewModel::class)
    internal abstract fun postListViewModel(viewModel: CoolViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    internal abstract fun messagesListViewModel(viewModel: DetailsViewModel): ViewModel

    //Add more ViewModels here
}