package com.example.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.synthetic.main.one.view.*
import kotlin.contracts.Returns

class My_Adapter (var elements:ArrayList<TASK1>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.one,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.NAME1.text = elements[position].Name
        holder.itemView.NAME2.text = elements[position].Description
        holder.itemView.NAME3.text = elements[position].Date
    }

    override fun getItemCount(): Int {
        return elements.size
    }

}