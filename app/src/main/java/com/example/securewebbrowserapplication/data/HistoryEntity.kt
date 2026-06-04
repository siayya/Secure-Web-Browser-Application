package com.example.securewebbrowserapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val url: String,

    val title: String,

    val visitCount: Int,

    val lastVisitedTime: Long
)