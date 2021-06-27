package com.example.retrofit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ItemImageBinding
import com.squareup.picasso.Picasso

class RvImageAdapter (var list: List<String>, var categoryName: String, var onItemClickListener: OnItemClickListener): RecyclerView.Adapter<RvImageAdapter.Vh>() {
    inner class Vh(var itemImageBinding: ItemImageBinding) :
        RecyclerView.ViewHolder(itemImageBinding.root) {

        fun onBind(string: String, categoryName: String) {
            Picasso.get().load(string).into(itemImageBinding.image)
            itemImageBinding.image.setOnClickListener {
                onItemClickListener.onClick(string, categoryName)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], categoryName)
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener {
        fun onClick(string: String, categoryName: String)
    }
}