# MemoNote – Application Android

Ce projet illustre la création d’une application de prise de notes sous Android. L’application est composée d’une liste de notes (List) et d’un écran de détail (Detail). Chaque note comprend un titre, un contenu et un nom de fichier permettant de la sauvegarder localement.

## Sommaire

1. [Concept](#concept)
2. [Technologies & Stack](#technologies--stack)
3. [Structure du Projet](#structure-du-projet)
4. [Étapes de Développement](#étapes-de-développement)
5. [Ressources & Codes Importants](#ressources--codes-importants)
6. [Auteurs & Licence](#auteurs--licence)

---

## Concept

L’idée est de proposer une **application simple** qui gère des notes :
- **MemoNoteList** affiche la liste des notes existantes via un `RecyclerView`.
- **MemoNoteDetail** gère l’affichage et l’édition (ou la création) d’une note.

Chaque note est **stockée localement** sur le dispositif. Le projet illustre notamment :
- La navigation entre deux activités (liste → détail).
- L’utilisation d’un `RecyclerView` et d’un `Adapter` pour lister les notes.
- L’usage d’un **Menu** pour les actions courantes (enregistrer, supprimer, etc.).

---

## Technologies & Stack

- **Langage** : [Kotlin](https://kotlinlang.org/)
- **SDK** : Android 8+ (minSdkVersion variable selon le Gradle)
- **Bibliothèques** :
    - **AndroidX** : AppCompat, RecyclerView, ConstraintLayout
    - **Material Components** : pour la `FloatingActionButton` et le thème Material
- **Architecture** : Architecture simple avec 2 activités (List et Detail) et un `Storage` pour la gestion des fichiers.

---

## Structure du Projet

Le projet est organisé comme suit :

```
app/src/main/
  ├─ AndroidManifest.xml
  ├─ java/com/cfa/memonote
  │    ├─ MemoNoteList.kt         // Activité listant les notes
  │    ├─ MemoNoteDetail.kt       // Activité affichant ou éditant une note
  │    ├─ classes
  │    │    ├─ Note.kt            // Data class pour les notes
  │    │    └─ NoteAdapter.kt     // Adapter pour le RecyclerView
  │    └─ utils
  │         └─ Storage.kt         // Fonctions de chargement/sauvegarde
  └─ res
       ├─ layout
       │    ├─ activity_memo_note_list.xml      // Layout principal pour la liste
       │    ├─ activity_memo_note_detail.xml    // Layout détail
       │    ├─ note.xml                         // Layout d'un item de note
       ├─ menu
       │    └─ menu_detail.xml                  // Menu pour enregistrer/supprimer
       ├─ values
       │    ├─ colors.xml                       // Couleurs personnalisées
       │    └─ styles.xml                       // Styles (ex: noteTitleFont)
       └─ drawable / mipmap                     // Ressources d’icônes
```

---

## Étapes de Développement

1. **Suppression de MainActivity & mainactivity.xml**
    - Élimination de l’ancienne activité (MainActivity) et de son layout.
    - Retrait de l’activité du `AndroidManifest.xml`.

2. **Création des nouvelles activités**
    - **MemoNoteList** : affiche un `RecyclerView` avec un `FloatingActionButton` pour ajouter une note.
    - **MemoNoteDetail** : présente ou édite le contenu d’une note existante.

3. **Configuration du Manifest**
    - Ajout de `MemoNoteList` comme activité principale (avec l’intent-filter `MAIN/LAUNCHER`).
    - Ajout de `MemoNoteDetail` comme activité complémentaire.

4. **Implémentation de la liste**
    - Création de `activity_memo_note_list.xml` avec un `CoordinatorLayout` englobant un `Toolbar` et un `RecyclerView`.
    - Ajout d’un bouton flottant (`FloatingActionButton`) pour la création de notes.

5. **Implémentation de l’écran de détail**
    - Création de `activity_memo_note_detail.xml` utilisant un `ConstraintLayout`.
    - Mise en place d’un menu `menu_detail.xml` pour les actions « Enregistrer » et « Supprimer ».

6. **Gestion des données**
    - **Note.kt** : Data class pour décrire une note (titre, texte, fileName).
    - **NoteAdapter.kt** : Gère l’affichage de chaque note dans le `RecyclerView`.
    - **Storage.kt** : Fonctions utilitaires pour charger et sauvegarder les notes sur le stockage interne du téléphone.

7. **Styles & Couleurs**
    - Définition de **colors.xml** pour un thème visuel cohérent (colorPrimary, colorAccent, etc.).
    - Création de **styles.xml** pour gérer la taille et le style de police (ex: `noteTitleFont`).

---

## Ressources & Codes Importants

### Extrait du `menu_detail.xml`
```xml
<menu
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto" >

    <item
        android:id="@+id/action_save"
        android:title="Enregistrer"
        android:icon="@drawable/ic_save_playstore"
        app:showAsAction="always"
        app:maxImageSize="40dp" />

    <item
        android:id="@+id/action_del"
        android:title="Supprimer"
        android:icon="@drawable/ic_delete_playstore"
        app:showAsAction="always"
        app:maxImageSize="40dp" />
</menu>
```

### Extrait de `activity_memo_note_list.xml`
```xml
<androidx.coordinatorlayout.widget.CoordinatorLayout
    ... >

    <RelativeLayout ... >
        <androidx.appcompat.widget.Toolbar
            android:id="@+id/note_toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="@color/colorPrimary" />

        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/note_recycler_view"
            ... />
    </RelativeLayout>

    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/new_fab_note"
        ...
        android:src="@drawable/ic_add_playstore" />

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

### Data class `Note.kt`
```kotlin
data class Note(
    val titre: String? = "",
    val texte: String? = "",
    val fileName: String? = ""
) : Parcelable, Serializable {
    ...
}
```

### Adapter `NoteAdapter.kt`
```kotlin
class NoteAdapter(
    val notes: MutableList<Note>,
    val itemClickListener: View.OnClickListener
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<CardView>(R.id.note_cardview)
        val titleView = itemView.findViewById<TextView>(R.id.title_note)
        val excerptView = itemView.findViewById<TextView>(R.id.excerpt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { ... }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { ... }

    override fun getItemCount(): Int { ... }
}
```

### Stockage local (`Storage.kt`)
```kotlin
fun loadNotes(context: Context): MutableList<Note> {
    val notes = mutableListOf<Note>()
    val noteDir = context.filesDir
    for (fileName in noteDir.list()) {
        val note = loadNotes(context, fileName)
        notes.add(note)
    }
    return notes
}

private fun loadNotes(context: Context, fileName: String): Note {
    val fileInput = context.openFileInput(fileName)
    val inputStream = ObjectInputStream(fileInput)
    val note = inputStream.readObject() as Note
    inputStream.close()
    return note
}
```

---

## Auteurs & Licence

- **Auteur** : [404Nomad]
- **Licence** : Ce projet est sous licence libre (MIT, Apache, etc.) ou licence propriétaire selon vos besoins.

*Pour toute question ou contribution, n’hésitez pas à ouvrir une Pull Request ou à contacter l’équipe de développement.*

---

Profitez de l’application *MemoNote* pour organiser et gérer vos notes de manière efficace.