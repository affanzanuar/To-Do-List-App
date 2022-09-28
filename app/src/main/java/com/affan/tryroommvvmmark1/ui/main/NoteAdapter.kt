package com.affan.tryroommvvmmark1.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.affan.tryroommvvmmark1.database.Note
import com.affan.tryroommvvmmark1.databinding.ItemNoteBinding
import com.affan.tryroommvvmmark1.helper.NoteDiffCallback
import com.affan.tryroommvvmmark1.ui.insert.NoteUpdateActivity

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder ( var binding : ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    private val listNote = ArrayList<Note>()

    fun setListNote (listNote : List <Note>) {
        val diffCallback = NoteDiffCallback(this.listNote,listNote)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNote.clear()
        this.listNote.addAll(listNote)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(ItemNoteBinding
            .inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = listNote[position]
        holder.binding.tvTitle.text = note.title
        holder.binding.tvDate.text = note.date
        holder.binding.tvDescription.text = note.description
        holder.binding.root.setOnClickListener {
            val intent = Intent(it.context,NoteUpdateActivity::class.java)
            intent.putExtra(NoteUpdateActivity.EXTRA_NOTE, note)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listNote.size
    }
}


