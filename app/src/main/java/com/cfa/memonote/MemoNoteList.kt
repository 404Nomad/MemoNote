package com.cfa.memonote

import android.app.Activity
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cfa.memonote.classes.Note
import com.cfa.memonote.classes.NoteAdapter
import com.cfa.memonote.utils.delNote
import com.cfa.memonote.utils.loadNotes
import com.cfa.memonote.utils.persistNote
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MemoNoteList : AppCompatActivity(), View.OnClickListener {

    val TAG = MemoNoteList::class.java.simpleName

    lateinit var coordinator: CoordinatorLayout
    lateinit var notes: MutableList<Note>
    lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_memo_note_list)

        coordinator = findViewById(R.id.coordinator)

        // Toolbar
        val note_toolbar = findViewById<Toolbar>(R.id.note_toolbar)
        setSupportActionBar(note_toolbar)

        // floating action button
        val new_note_fab = findViewById<FloatingActionButton>(R.id.new_fab_note)
        new_note_fab.setOnClickListener(this)

        // charge les notes
        notes = loadNotes(this)

        // construit les items du recycler
        adapter = NoteAdapter(notes, this)

        // construit le recycler
        val recyclerView: RecyclerView = findViewById(R.id.note_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    // OnClick Condition
    override fun onClick(v: View?) {
        if (v?.tag != null) { //element de liste
            showDetailNote(v.tag as Int)
        } else { // je suis sur le bouton flottant
            when (v?.id) {
                R.id.new_fab_note -> createNewNote()
            }
        }
    }

    // affiche le detail d'une note
    private fun showDetailNote(i: Int) {

        // cas created note, si i < 0, on cree une note vide sinon on prend la note en cours
        val note: Note = if (i < 0) Note() else notes[i]

        // navigation
        val intent = Intent(this, MemoNoteDetail::class.java)
        intent.putExtra(MemoNoteDetail.NOTE_EXTRA, note as Parcelable)
        intent.putExtra(MemoNoteDetail.NOTE_EXTRA_INDEX, i)

        editNoteLauncher.launch(intent)
    }

    private val editNoteLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                processEditNoteDetail(result.data!!)
            }
        }

    private fun processEditNoteDetail(data: Intent) {
        val noteIndex = data.getIntExtra(MemoNoteDetail.NOTE_EXTRA_INDEX, -1)
        when (data.action) {
            MemoNoteDetail.ACTION_SAVE_NOTE -> {
                val note = data.getParcelableExtra(MemoNoteDetail.NOTE_EXTRA, Note::class.java)
                saveNote(note, noteIndex)

            }

            MemoNoteDetail.ACTION_DELETE_NOTE -> {
                deleteNote(noteIndex)
            }
        }
    }

    private fun deleteNote(noteIndex: Int) {
        if (noteIndex < 0)
            return
        val note = notes.removeAt(noteIndex)
        delNote(this, note)
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "\"${note.titre}\" deleted", Toast.LENGTH_SHORT).show()

    }

    private fun saveNote(note: Note?, noteIndex: Int) {
        persistNote(this, note as Note)
        if (noteIndex < 0) {
            notes.add(0, note)
        } else {
            notes[noteIndex] = note
        }
        adapter.notifyDataSetChanged()
    }

    // rapeller showDetailNote avec index inexistant : -1
    private fun createNewNote() {
        showDetailNote(-1)
    }

}
