package com.cfa.memonote

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cfa.memonote.classes.Note
import com.cfa.memonote.fragment.ConfirmDeleteNoteFragment

class MemoNoteDetail : AppCompatActivity() {

    companion object {
        val NOTE_EXTRA = "note"
        val NOTE_EXTRA_INDEX = "noteIndex"
        val REQUEST_EDIT_NOTE = 1

        // sauvegardee supression
        val ACTION_SAVE_NOTE = "com.cfa.memonote.actions.ACTION_SAVE_NOTE"
        val ACTION_DELETE_NOTE = "com.cfa.memonote.actions.ACTION_DELETE_NOTE"
    }

    // dans cette vue qui affiche detail note on crée variable a lateinit
    lateinit var note: Note
    var noteIndex = -1

    // pour les champs de formulaire
    lateinit var titleView: TextView
    lateinit var texteView: TextView

    // créer options menu + surcharge avec inflater
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    // aprés avoior surcharge fonction pour agir quand on lcique sur bouton menu
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> {
                saveNote()
                return true
            }

            R.id.action_del -> {
                //appel modale de confirmation
                showConfirmNoteDialog()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    // fenetre Modale apart avec heritage et events + surcharge a l'appel
    private fun showConfirmNoteDialog() {
        val confirmDialog = ConfirmDeleteNoteFragment(note.titre.toString())
        confirmDialog.listener = object : ConfirmDeleteNoteFragment.ConfirmDialogListener {
            override fun onNegativeClick() {

            }

            override fun onPositiveClick() {
                deleteNote()
            }
        }
        confirmDialog.show(supportFragmentManager, "confirmDeleteDialog")
    }

    // sauvegarde
    private fun saveNote() {
        note.titre = titleView.text.toString()
        note.texte = texteView.text.toString()

        if (note.titre!!.isEmpty() || note.texte!!.isNotEmpty()) {
            intent = Intent(ACTION_SAVE_NOTE)
            intent.putExtra(NOTE_EXTRA, note as Parcelable)
            intent.putExtra(NOTE_EXTRA_INDEX, noteIndex)
            setResult(Activity.RESULT_OK, intent)
            finish()
        } else {
            Toast.makeText(
                this, "Vous devez remplir le champ titre et le champ texte",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    // suppression
    private fun deleteNote() {
        intent = Intent(ACTION_DELETE_NOTE)
        intent.putExtra(NOTE_EXTRA_INDEX, noteIndex)
        setResult(Activity.RESULT_OK, intent) // definition du résultat à renvoyer à activityList
        finish()
    }

    // onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo_note_detail)

        // toolbar
        val toolbar = findViewById<Toolbar>(R.id.note_toolbar)
        setSupportActionBar(toolbar)

        // alimenter en passant par systemIntent
        note = intent.getParcelableExtra(NOTE_EXTRA, Note::class.java)!!
        noteIndex = intent.getIntExtra(NOTE_EXTRA_INDEX, -1)

        titleView = findViewById(R.id.edit_title)
        texteView = findViewById(R.id.edit_text)

        titleView.text = note.titre
        texteView.text = note.texte
    }

}