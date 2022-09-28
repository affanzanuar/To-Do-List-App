package com.affan.tryroommvvmmark1.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.affan.tryroommvvmmark1.database.Note
import com.affan.tryroommvvmmark1.repository.NoteRepository

class MainViewModel (application: Application) : ViewModel() {

    private var mNoteRepository : NoteRepository = NoteRepository(application)

    fun getNotesByID () : LiveData<List<Note>> = mNoteRepository.getAllNotes()

}