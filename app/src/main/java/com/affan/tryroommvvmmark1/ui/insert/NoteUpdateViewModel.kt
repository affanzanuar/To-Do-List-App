package com.affan.tryroommvvmmark1.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.affan.tryroommvvmmark1.database.Note
import com.affan.tryroommvvmmark1.repository.NoteRepository

class NoteUpdateViewModel (application: Application) : ViewModel() {

    private var mNoteRepository : NoteRepository = NoteRepository(application)

    fun addNote (data : Note) {
        mNoteRepository.addNote(data)
    }

    fun updateNote (data : Note) {
        mNoteRepository.updateNote(data)
    }

    fun deleteNote (data : Note) {
        mNoteRepository.deleteNote(data)
    }



}