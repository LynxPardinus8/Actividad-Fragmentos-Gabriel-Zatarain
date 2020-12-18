package com.example.thegmd2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thegmd2.R
import com.example.thegmd2.model.Student

class RecyclerViewAdapter(var studentList: List<Student>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder>() {
    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView = itemView.findViewById<TextView>(R.id.li_TV)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val student = studentList[position]
        holder.idTextView.text = student.nombre + " " + student.apellido
    }

    override fun getItemCount(): Int = studentList.size
}
