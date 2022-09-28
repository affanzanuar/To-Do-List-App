package com.affan.tryroommvvmmark1.ui.insert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.affan.tryroommvvmmark1.R
import com.affan.tryroommvvmmark1.ViewModelFactory
import com.affan.tryroommvvmmark1.database.Note
import com.affan.tryroommvvmmark1.databinding.ActivityNoteUpdateBinding
import com.affan.tryroommvvmmark1.helper.DateHelper
import com.affan.tryroommvvmmark1.ui.main.MainActivity

class NoteUpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteUpdateBinding
    private var isEdit = false
    private var note : Note? = null
    private lateinit var noteUpdateViewModel: NoteUpdateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteUpdateViewModel = obtainViewModel(this)
        initGetData()
        getClickListener()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (isEdit){
            menuInflater.inflate(R.menu.menu_form, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionDelete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home ->showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    private fun showAlertDialog(type : Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle : String
        val dialogMessage : String
        if (isDialogClose) {
            dialogTitle = "cancel"
            dialogMessage = "Pesan waktu cancel"
        } else {
            dialogTitle = "delete"
            dialogMessage = " Pesan waktu delete"
        }

        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton("yes") { _,_ ->
                if (!isDialogClose) {
                    noteUpdateViewModel.deleteNote(note as Note)
                    getToast("telah dihapus")
                }
                finish()
            }
            setNegativeButton("No") { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

    }

    private fun getToast (message : String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    private fun getClickListener () {
        binding.btnSubmit.setOnClickListener {
            val title = binding.edtTitle.text.toString().trim()
            val description = binding.edtDescription.text.toString().trim()

            when {
                title.isEmpty() -> {
                    binding.edtTitle.error = "Title tidak boleh kosong"
                }

                description.isEmpty() -> {
                    binding.edtDescription.error = "Deskripsi tidak boleh kosong"
                }
                else -> {
                    note.let { note ->
                        note?.title = title
                        note?.description = description
                    }
                    if (isEdit) {
                        noteUpdateViewModel.updateNote(note as Note)
                        Toast.makeText(this,"Telah dirubah",Toast.LENGTH_SHORT).show()
                    }
                    else {
                        note.let { note ->
                            note?.date = DateHelper.getCurrentDate()
                        }
                        noteUpdateViewModel.addNote(note as Note)
                        Toast.makeText(this,"Telah ditambah",Toast.LENGTH_SHORT).show()
                    }
                    finish()
                }
            }
        }
    }

    private fun initGetData () {
        note = intent.getParcelableExtra(EXTRA_NOTE)
        if (note != null) {
            isEdit = true
        } else {
            note = Note()
        }

        val actionBarTitle : String
        val btnTitle : String

        if (isEdit) {
            actionBarTitle = "Melakukan Perubahan"
            btnTitle = "Melakukan update"

            if (note != null) {
                note?.let { note ->
                    binding.edtTitle.setText(note.title)
                    binding.edtDescription.setText(note.description)
                }
            }
        } else {
            actionBarTitle = "Melakukan menambah"
            btnTitle = "Melakukan simpan"
        }

        supportActionBar?.title  = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSubmit.text = btnTitle
    }


    private fun obtainViewModel(activity: AppCompatActivity): NoteUpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity,factory).get(NoteUpdateViewModel::class.java)
    }

    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }
}