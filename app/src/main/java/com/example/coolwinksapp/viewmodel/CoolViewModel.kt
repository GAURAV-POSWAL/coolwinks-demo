package com.example.coolwinksapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.coolwinksapp.model.CoolDataRepository
import javax.inject.Inject

class CoolViewModel @Inject constructor(private val coolRepo: CoolDataRepository) :
    ViewModel() {

}

