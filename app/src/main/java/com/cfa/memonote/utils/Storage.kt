package com.cfa.memonote.utils

import android.content.Context
import android.text.TextUtils
import com.cfa.memonote.classes.Note
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.UUID

// recupere les chemins des fichiers de note
fun loadNotes(context: Context): MutableList<Note> {

    // table mutable vide de type note que l'on remplira
    val notes = mutableListOf<Note>()

    // definir un repertoire ou seronts stockées les notes
    val noteDir = context.filesDir

    // Filtrer uniquement les fichiers qui se terminent par ".note"
    val files = noteDir.list()?.filter { it.endsWith(".note") } ?: emptyList()

    // parcourir les filename
    for (fileName in files) {
        try {
            val note = loadNote(context, fileName)
            notes.add(note)
        } catch (e: Exception) {
            e.printStackTrace()
            // Optionnel : supprimer le fichier corrompu
            // context.deleteFile(fileName)
        }
    }
    return notes
}

private fun loadNote(context: Context, fileName: String): Note {
    context.openFileInput(fileName).use { fileInput ->
        ObjectInputStream(fileInput).use { inputStream ->
            return inputStream.readObject() as Note
        }
    }
}
// ouvre, lis et caste dans l'objeet
//private fun loadNote(context: Context, fileName: String): Note {
//
//    // ouvrir le fichier represente par fileName avec fileinput
//    val fileInput = context.openFileInput(fileName)
//
//    // streamer cette ouverture dans un objet
//    val inputStream = ObjectInputStream(fileInput)
//
//    // lire l'objet en tant que note ( analiser et caster )
//    val note = inputStream.readObject() as Note
//
//    // fermer
//    inputStream.close()
//
//    return note
//
//}


// persister une note
fun persistNote(context: Context, note: Note) {
    if (TextUtils.isEmpty(note.fileName)) {
        note.fileName = UUID.randomUUID().toString() + ".note"
    }

    // cree empty file
    val fileOutput = context.openFileOutput(note.fileName, Context.MODE_PRIVATE)

    // créer un string pour le remplir
    val outputStream = ObjectOutputStream(fileOutput)

    // écrire
    outputStream.writeObject(note)

    // fermer
    outputStream.close()
}

// fonction pour supprimer une note
fun delNote(context: Context, note: Note) {
    context.deleteFile(note.fileName)
}