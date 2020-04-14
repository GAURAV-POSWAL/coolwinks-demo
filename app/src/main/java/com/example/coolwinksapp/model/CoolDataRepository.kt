package com.example.coolwinksapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coolwinksapp.network.CoolApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class CoolDataRepository @Inject constructor(private val coolApiService: CoolApiService) {

    // hit the apis here
    fun getUserData(): LiveData<CoolApiViewDataResponse> {

        val usersMessageData: MutableLiveData<CoolApiViewDataResponse> = MutableLiveData()

        coolApiService.getUsersMessageData().enqueue(object : Callback<CoolApiResponse> {
            override fun onResponse(
                call: Call<CoolApiResponse>,
                response: Response<CoolApiResponse?>
            ) {
                if (response.isSuccessful) {
//                    usersMessageData.value = response.body()
                } else {
                    usersMessageData.value = null
                }
            }

            override fun onFailure(call: Call<CoolApiResponse>, t: Throwable?) {
                usersMessageData.value = null
            }
        })
        return usersMessageData

    }

}