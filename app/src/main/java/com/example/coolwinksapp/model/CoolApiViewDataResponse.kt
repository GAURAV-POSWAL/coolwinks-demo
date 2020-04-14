package com.example.coolwinksapp.model

data class CoolApiViewDataResponse(
    val userId: String,
    val messagesList: List<Message>
)