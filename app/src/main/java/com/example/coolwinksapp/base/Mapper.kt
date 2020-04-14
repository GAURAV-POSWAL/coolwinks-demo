package com.example.coolwinksapp.base

interface Mapper<in Src, out Des> {
    fun map(srcObject: Src): Des
}