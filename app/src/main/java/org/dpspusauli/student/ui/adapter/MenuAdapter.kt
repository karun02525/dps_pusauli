package org.dpspusauli.student.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_menu.view.*
import org.dpspusauli.R
import org.dpspusauli.student.model.MenuModel


class MenuAdapter(var list: List<MenuModel> = listOf(),var listener: ItemClickListener) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClicked(repos: MenuModel,pos:Int)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_menu, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindItems(model: MenuModel) {
            itemView.apply {
                img.setImageResource(model.thumbnail)
                txt.text=model.title
                }.setOnClickListener {
                listener.onItemClicked(model,adapterPosition)
            }
            }

    }
}