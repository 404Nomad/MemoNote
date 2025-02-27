package com.cfa.memonote.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ConfirmDeleteNoteFragment(var noteTitle: String) : DialogFragment() {

    // interface
    interface ConfirmDialogListener {
        fun onPositiveClick()
        fun onNegativeClick()
    }

    var listener: ConfirmDialogListener? = null

    // retourne objet Dialog
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(activity)
        builder.setMessage("Etes-vous sur de vouloir supprimer la note : \"$noteTitle\" ?")
            .setPositiveButton(
                "Supprimer",
                DialogInterface.OnClickListener { dialog, id -> listener?.onPositiveClick() })
            .setNegativeButton(
                "Annuler",
                DialogInterface.OnClickListener { dialog, id -> listener?.onNegativeClick() })
        return builder.create()
    }

}