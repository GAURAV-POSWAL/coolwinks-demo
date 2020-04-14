package com.example.coolwinksapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coolwinksapp.model.CoolDataRepository
import com.example.coolwinksapp.model.CoolViewDataResponse
import javax.inject.Inject

class CoolViewModel @Inject constructor(private val coolRepo: CoolDataRepository) :
    ViewModel() {

    private var usersData: List<CoolViewDataResponse>? = null

    fun getUsersApiData(): LiveData<List<CoolViewDataResponse>> = coolRepo.getUserData()

    fun setUsersData(usersData: List<CoolViewDataResponse>?) {
        this.usersData = usersData
    }

    fun getUsersData() = usersData
}

