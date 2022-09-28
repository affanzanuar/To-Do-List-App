package com.affan.tryroommvvmmark1.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.affan.tryroommvvmmark1.database.Note
import com.affan.tryroommvvmmark1.database.NoteDao
import com.affan.tryroommvvmmark1.database.NoteDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository (application: Application) {

    private val mNoteDao : NoteDao
    private val execute : ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteDatabase.getDatabase(application)
        mNoteDao = db.noteDao()
    }

    fun getAllNotes () : LiveData<List<Note>> = mNoteDao.getNoteByID()

    fun addNote (data : Note) {
        execute.execute {mNoteDao.addNote(data)}
    }

    fun updateNote (data : Note) {
        execute.execute { mNoteDao.updateNote(data) }
    }

    fun deleteNote (data : Note) {
        execute.execute { mNoteDao.deleteNote(data) }
    }



}