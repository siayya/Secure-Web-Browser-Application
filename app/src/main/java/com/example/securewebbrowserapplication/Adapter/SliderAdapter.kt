package com.example.securewebbrowserapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.securewebbrowserapplication.databinding.ItemSliderBinding
import com.example.securewebbrowserapplication.model.SliderItem

class SliderAdapter(
    private val items: List<SliderItem>
): RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(
        val binding: ItemSliderBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderViewHolder {

        val binding = ItemSliderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(

        holder: SliderViewHolder,
        position: Int
    ) {


        holder.binding.imageView.setImageResource(items[position].image)
        holder.binding.tvTitle.text = items[position].title
        holder.binding.tvDescription.text = items[position].description
    }

    override fun getItemCount() = items.size
}