package com.example.coolwinksapp.model

import com.google.gson.annotations.SerializedName

data class CoolApiResponse(
    @SerializedName("type") val resultType: String,
    @SerializedName("display") val title: String,
    @SerializedName("class") val resultClass: String,
    @SerializedName("course") val resultCourse: String,
    @SerializedName("chapter") val chapter: String
)