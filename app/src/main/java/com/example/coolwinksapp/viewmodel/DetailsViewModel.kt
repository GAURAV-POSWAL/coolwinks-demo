package com.example.coolwinksapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.coolwinksapp.model.CoolDataRepository
import com.example.coolwinksapp.model.Message
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val coolRepo: CoolDataRepository) :
    ViewModel() {

    private var usersMessagesData: List<Message>? = null

    fun setUsersMessagesData(usersData: List<Message>?) {
        this.usersMessagesData = usersData
    }

    fun getUsersMessagesData() = usersMessagesData
}

