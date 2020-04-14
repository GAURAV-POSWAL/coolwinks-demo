package com.example.coolwinksapp.mapper

import com.example.coolwinksapp.base.Mapper
import com.example.coolwinksapp.model.CoolApiResponse
import com.example.coolwinksapp.model.CoolViewDataResponse
import com.example.coolwinksapp.model.Message
import javax.inject.Inject


class UserDataItemMapper @Inject constructor() :
    Mapper<List<CoolApiResponse>?, List<CoolViewDataResponse>?> {

    override fun map(srcObject: List<CoolApiResponse>?): List<CoolViewDataResponse> =
        getViewData(srcObject)

    private fun getViewData(srcObject: List<CoolApiResponse>?): List<CoolViewDataResponse> {

        val dataMap: LinkedHashMap<Int, ArrayList<CoolApiResponse>> = LinkedHashMap()

        srcObject?.let {
            for (element in srcObject) {

                if (dataMap.containsKey(element.userId)) {
                    dataMap[element.userId]?.add(element)
                } else {
                    val list = ArrayList<CoolApiResponse>()
                    list.add(element)
                    dataMap[element.userId] = list
                }
            }
        }

        val dataList = ArrayList<CoolViewDataResponse>()

        dataMap?.let {
            for (dataEntry in dataMap.entries) {
                val dataObject = CoolViewDataResponse(
                    dataEntry.key.toString(), ArrayList()
                )
                for (entryList in dataEntry.value) {
                    dataObject.messagesList.add(
                        Message(
                            entryList.id.toString(),
                            entryList.title,
                            entryList.body
                        )
                    )
                }
                dataList.add(dataObject)
            }
        }

        return dataList
    }
}