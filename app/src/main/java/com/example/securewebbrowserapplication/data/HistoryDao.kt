package com.example.securewebbrowserapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HistoryDao {

    @Query(
        "SELECT * FROM history WHERE url = :url LIMIT 1"
    )
    suspend fun getByUrl(
        url: String
    ): HistoryEntity?

    @Insert
    suspend fun insert(
        history: HistoryEntity
    )

    @Update
    suspend fun update(
        history: HistoryEntity
    )

    @Query(
        "SELECT * FROM history ORDER BY lastVisitedTime DESC"
    )
    suspend fun getAllHistory(): List<HistoryEntity>

    @Query(
        "DELETE FROM history"
    )
    suspend fun clearHistory()
}