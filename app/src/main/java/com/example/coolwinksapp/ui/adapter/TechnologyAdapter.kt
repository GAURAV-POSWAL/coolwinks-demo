package com.example.coolwinksapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coolwinksapp.R
import kotlinx.android.synthetic.main.row_item_tech.view.*

class TechnologyAdapter(
    private val context: Context, private val techList: Array<String>

) : RecyclerView.Adapter<TechnologyAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.row_item_tech,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return techList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvTechUsed.text = techList[position]
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvTech: TextView? = null

        init {
            tvTech = itemView.tvTechUsed

        }

    }

}

