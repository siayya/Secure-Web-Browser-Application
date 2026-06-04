package com.example.securewebbrowserapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.securewebbrowserapplication.data.HistoryEntity
import com.example.securewebbrowserapplication.databinding.ItemHistoryBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryAdapter(
    private var historyList: List<HistoryEntity>
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(
        val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryViewHolder {

        val binding = ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HistoryViewHolder,
        position: Int
    ) {

        val item = historyList[position]

        holder.binding.tvTitle.text = item.title
        holder.binding.tvUrl.text = item.url

        holder.binding.tvVisitCount.text =
            "Visits: ${item.visitCount}"

        val date =
            SimpleDateFormat(
                "dd MMM yyyy hh:mm a",
                Locale.getDefault()
            ).format(Date(item.lastVisitedTime))

        holder.binding.tvLastVisit.text =
            "Last Visit: $date"
    }

    override fun getItemCount() =
        historyList.size

    fun updateData(
        newList: List<String?>
    ) {
        historyList = historyList
        notifyDataSetChanged()
    }
}