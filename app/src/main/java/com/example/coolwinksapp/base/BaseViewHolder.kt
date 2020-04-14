package com.example.coolwinksapp.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {


    var actionPerformer: ActionPerformer<Action>? = null

    abstract fun bind(data: T)

    open fun performAction(action: Action) {
        this.actionPerformer?.performAction(action)
    }
}