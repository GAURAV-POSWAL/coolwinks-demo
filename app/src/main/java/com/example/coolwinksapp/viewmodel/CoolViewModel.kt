package com.example.coolwinksapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coolwinksapp.model.CoolApiViewDataResponse
import com.example.coolwinksapp.model.CoolDataRepository
import javax.inject.Inject

class CoolViewModel @Inject constructor(private val coolRepo: CoolDataRepository) :
    ViewModel() {

    private var usersData: CoolApiViewDataResponse? = null

    fun getUsersData(): LiveData<CoolApiViewDataResponse> = coolRepo.getUserData()

    fun setUsersData(usersData: CoolApiViewDataResponse?) {
        this.usersData = usersData
    }

}

