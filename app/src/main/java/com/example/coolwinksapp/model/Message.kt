package com.example.coolwinksapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Message(
    val messageId: String,
    val messageTitle: String,
    val messageBody: String,
    var isExapnded: Boolean = false
) : Parcelable