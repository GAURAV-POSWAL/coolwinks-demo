package com.example.coolwinksapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coolwinksapp.mapper.UserDataItemMapper
import com.example.coolwinksapp.network.CoolApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class CoolDataRepository @Inject constructor(
    private val coolApiService: CoolApiService,
    private val mapper: UserDataItemMapper
) {

    // hit the apis here
    fun getUserData(): LiveData<List<CoolViewDataResponse>> {

        val usersMessageData: MutableLiveData<List<CoolViewDataResponse>> = MutableLiveData()

        coolApiService.getUsersMessageData().enqueue(object : Callback<List<CoolApiResponse>> {
            override fun onResponse(
                call: Call<List<CoolApiResponse>>,
                response: Response<List<CoolApiResponse>?>
            ) {
                if (response.isSuccessful) {
                    usersMessageData.value = mapper.map(response.body())
                } else {
                    usersMessageData.value = null
                }
            }

            override fun onFailure(call: Call<List<CoolApiResponse>>, t: Throwable?) {
                usersMessageData.value = null
            }
        })
        return usersMessageData

    }

}