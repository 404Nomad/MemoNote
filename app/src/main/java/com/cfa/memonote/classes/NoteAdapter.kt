package com.cfa.memonote.classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cfa.memonote.R

class NoteAdapter(
    val notes: MutableList<Note>,
    val itemClickListener: View.OnClickListener
): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<CardView>(R.id.note_cardview)
        val titleView = itemView.findViewById<TextView>(R.id.note_cardview)
        val excerptView = itemView.findViewById<TextView>(R.id.excerpt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        val viewitem = LayoutInflater.from(parent.context).inflate(R.layout.note, parent, false)
        return ViewHolder(viewitem)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        val note = notes[position]
        holder.cardView.tag = position
        holder.titleView.text = note.titre
        holder.excerptView.text = note.texte
        holder.cardView.setOnClickListener(itemClickListener)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

}