package com.cfa.memonote

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MemoNoteList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_memo_note_list)

        // Utilisez l'id du CoordinatorLayout défini dans votre layout (ici "coordinator")
        val coordinator = findViewById<androidx.coordinatorlayout.widget.CoordinatorLayout>(R.id.coordinator)
        ViewCompat.setOnApplyWindowInsetsListener(coordinator) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
