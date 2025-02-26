package com.cfa.memonote.utils

import android.content.Context
import com.cfa.memonote.classes.Note
import java.io.ObjectInputStream

// recupere les chemins des fichiers de note
fun loadNotes(context: Context): MutableList<Note> {

    // table mutable vide de type note que l'on remplira
    val notes = mutableListOf<Note>()

    // definir un repertoire ou seronts stockées les notes
    val noteDir = context.filesDir

    // parcourir les filename
    for(fileName in noteDir.list()) {

        // affecter une note
        val note = loadNotes(context, fileName)

        // ajouter la note
        notes.add(note)
    }
    return notes
}

// ouvre, lis et caste dans l'objeet
private fun loadNotes(context: Context, fileName: String): Note {

    // ouvrir le fichier represente par fileName avec fileinput
    val fileInput = context.openFileInput(fileName)

    // streamer cette ouverture dans un objet
    val inputStream = ObjectInputStream(fileInput)

    // lire l'objet en tant que note ( analiser et caster )
    val note = inputStream.readObject() as Note

    // fermer
    inputStream.close()

    return Note()
}