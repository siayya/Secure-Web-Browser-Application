package com.example.securewebbrowserapplication.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.securewebbrowserapplication.Adapter.HistoryAdapter
import com.example.securewebbrowserapplication.data.AppDatabase
import com.example.securewebbrowserapplication.databinding.ActivityHistoryScreenBinding
import kotlinx.coroutines.launch

class HistoryScreen : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryScreenBinding
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            ActivityHistoryScreenBinding.inflate(
                layoutInflater
            )

        setContentView(binding.root)

        adapter =
            HistoryAdapter(emptyList())

        binding.rvHistory.layoutManager =
            LinearLayoutManager(this)

        binding.rvHistory.adapter =
            adapter

        loadHistory()

        val prefs =
            getSharedPreferences(
                "history",
                MODE_PRIVATE
            )

        val urls =
            prefs.getStringSet(
                "urls",
                emptySet()
            )?.toList() ?: emptyList()

        binding.tvEmpty.text = urls.joinToString("\n")
        binding.tvEmpty.visibility = View.VISIBLE

        binding.backBt.setOnClickListener {
            finish()
        }

        binding.btnClearHistory.setOnClickListener {

            val prefs =
                getSharedPreferences(
                    "history",
                    MODE_PRIVATE
                )

            prefs.edit()
                .remove("history_list")
                .remove("urls")
                .apply()

            binding.tvEmpty.text =
                "No browsing history found"

        }
    }

    private fun loadHistory() {

        lifecycleScope.launch {

            val prefs =
                getSharedPreferences(
                    "history",
                    MODE_PRIVATE
                )

            val urls =
                prefs.getStringSet(
                    "urls",
                    emptySet()
                )

            Toast.makeText(
                this@HistoryScreen,
                urls.toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}