package com.affan.tryroommvvmmark1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [Note::class],
    version = 1
)

abstract class NoteDatabase : RoomDatabase () {

    abstract fun noteDao () : NoteDao

    companion object {

        @Volatile
        private var instace : NoteDatabase? = null

        @JvmStatic
        fun getDatabase (context: Context) : NoteDatabase{
            if (instace==null){
                kotlin.synchronized(NoteDatabase::class.java) {
                    instace = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "note-database"
                    ).build()
                }
            }
            return instace as NoteDatabase
        }
    }
}