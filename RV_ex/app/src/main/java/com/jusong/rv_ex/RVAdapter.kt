package com.jusong.rv_ex

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(val items : MutableList<String>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)

        return ViewHolder(view)
    }

    //+
    interface ItemClick {
        fun onClick(view : View, position: Int)
    }
    var itemClick : ItemClick? = null
    //

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //+
        if(itemClick != null) {
            holder.itemView.setOnClickListener { v->
                itemClick?.onClick(v, position)
            }
        }
        //
        holder.bindItems(items[position])
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(item : String) {
            val rv_text = itemView.findViewById<TextView>(R.id.rvItem)
            rv_text.text = item
        }
    }

}