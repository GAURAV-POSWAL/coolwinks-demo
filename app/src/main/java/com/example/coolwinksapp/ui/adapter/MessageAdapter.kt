package com.example.coolwinksapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.coolwinksapp.R
import com.example.coolwinksapp.model.Message
import kotlinx.android.synthetic.main.row_item_messages.view.*


class MessageAdapter(
    private val context: Context
) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    private var lastExpandedItemPosition: Int = -1
    private var messagesList = emptyList<Message>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.row_item_messages, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount() = messagesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = messagesList[position]
        holder.bindPhoto(article)
    }

    fun updateList(newData: List<Message>) {
        messagesList = newData
        notifyDataSetChanged()

    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (lastExpandedItemPosition > -1)
                messagesList[lastExpandedItemPosition].isExapnded = false
            messagesList[adapterPosition].isExapnded = true
            lastExpandedItemPosition = adapterPosition
            notifyDataSetChanged()
        }


        fun bindPhoto(userData: Message) {
            view.tvMsgTitle.text = userData.messageTitle
            view.tvMsgContent.text = userData.messageBody
            view.tvMsgContentDetail.text = userData.messageBody
            if (userData.isExapnded) {
                view.tvMsgContentDetail.visibility = View.VISIBLE
            } else {
                view.tvMsgContentDetail.visibility = View.GONE
            }
        }
    }

}

private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}