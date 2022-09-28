package com.affan.tryroommvvmmark1.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun addNote (data : Note)

    @Update
    fun updateNote (data : Note)

    @Delete
    fun deleteNote (data : Note)

    @Query ("SELECT * FROM note ORDER BY id ASC")
    fun getNoteByID () : LiveData<List<Note>>
}