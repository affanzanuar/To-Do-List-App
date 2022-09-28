package com.affan.tryroommvvmmark1.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.affan.tryroommvvmmark1.ViewModelFactory
import com.affan.tryroommvvmmark1.databinding.ActivityMainBinding
import com.affan.tryroommvvmmark1.ui.insert.NoteUpdateActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NoteAdapter()

        binding.rvAllNotes.layoutManager = LinearLayoutManager(this)
        binding.rvAllNotes.adapter = adapter

        val mainViewModel = obtainViewModel(this)
        mainViewModel.getNotesByID().observe(this) { noteList ->
            if (noteList != null) {
                adapter.setListNote(noteList)
            }
        }

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, NoteUpdateActivity::class.java)
            startActivity(intent)
        }

    }

    private fun obtainViewModel (activity: AppCompatActivity) : MainViewModel{
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }
}