package com.example.coolwinksapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.coolwinksapp.R
import com.example.coolwinksapp.model.CoolViewDataResponse
import com.example.coolwinksapp.ui.activity.UserDetailsActivity
import com.example.coolwinksapp.ui.activity.UserDetailsActivity.Companion.MESSAGES_DATA
import kotlinx.android.synthetic.main.row_item_user.view.*


class UsersListAdapter(private val mContext: Context) :
    RecyclerView.Adapter<UsersListAdapter.UserViewHolder>() {

    private var usersDataList = listOf<CoolViewDataResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflatedView = parent.inflate(R.layout.row_item_user, false)
        return UserViewHolder(inflatedView)
    }

    override fun getItemCount() = usersDataList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val article = usersDataList[position]
        holder.bindPhoto(article)
    }

    fun updateList(newData: List<CoolViewDataResponse>) {
        usersDataList = newData
        notifyDataSetChanged()

    }

    inner class UserViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v

        init {

            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(mContext, UserDetailsActivity::class.java)
            intent.putExtra(MESSAGES_DATA, usersDataList[adapterPosition].messagesList)
            mContext.startActivity(intent)
        }


        fun bindPhoto(userData: CoolViewDataResponse) {
            view.userID.text = "User ${userData.userId}"
        }
    }


}

private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

