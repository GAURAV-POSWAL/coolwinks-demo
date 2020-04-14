package com.example.coolwinksapp.base

interface ActionPerformer<ACTION> {
    fun performAction(action: ACTION)
}