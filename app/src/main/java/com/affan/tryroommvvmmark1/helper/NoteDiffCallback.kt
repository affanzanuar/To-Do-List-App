package com.affan.tryroommvvmmark1.helper

import androidx.recyclerview.widget.DiffUtil
import com.affan.tryroommvvmmark1.database.Note

class NoteDiffCallback (
    private val mOldNoteList : List<Note>,
    private val mNewNoteList : List<Note>,
        ) : DiffUtil.Callback () {

    override fun getOldListSize(): Int {
        return mOldNoteList.size
    }

    override fun getNewListSize(): Int {
        return mNewNoteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mNewNoteList[newItemPosition].id == mOldNoteList[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = mOldNoteList[oldItemPosition]
        val new = mNewNoteList[newItemPosition]
        return old.title == new.title && old.description == new.description
    }
}