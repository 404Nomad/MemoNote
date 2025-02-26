package com.cfa.memonote.classes

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

// nos trois variables
data class Note(
    val titre: String? = "",
    val texte: String? = "",
    val fileName: String? = "",
): Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    // definir
    override fun writeToParcel(dest: Parcel, p1: Int) {
        dest.writeString(titre)
        dest.writeString(texte)
        dest.writeString(fileName)
    }

    companion object CREATOR : Parcelable.Creator<Note> {

        // serialisation pour identifier de facon unique chaque objet crée
        private val serialVersionUID: Long = 165464133 // Serialisation de lo'bjet

        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }

}
