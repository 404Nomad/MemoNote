on delete MainAcitivty

on delete mainactivity.xml

on delete activity dans le manifest

on crée notre List et Detail :

- new MemoNoteList.kt et activity_memo_note_list.xml
- new MemoNoteDetail.kt et activity_memo_note_detail.xml

new Android Ressource Directory Menu type Menu

on commence par créer un menu

menu > new menu_detail.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    >
    <item
        android:id="@+id/action_save"
        app:showAsAction="always"
        android:icon="@drawable/ic_save_playstore"
        android:title="Enregistrer"
        app:maxImageSize="40dp"
        />
    <item
        android:id="@+id/action_del"
        app:showAsAction="always"
        android:icon="@drawable/ic_delete_playstore"
        android:title="Supprimer"
        app:maxImageSize="40dp"
        />

</menu>
```

dans active_memo_note_list.xml on crée

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"

    tools:context=".MemoNoteList"
    android:id="@+id/coordinator"

    android:layout_width="match_parent"
    android:layout_height="match_parent" >
    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <!--Premier composant toolbar -->
        <androidx.appcompat.widget.Toolbar
            android:id="@+id/note_toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="@color/colorPrimary"
            >
        </androidx.appcompat.widget.Toolbar>

        <!-- Deuxieme composant recyclerView qui recupere liste de notes -->
        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/note_recycler_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_below="@+id/note_toolbar"
            tools:listitem="@layout/note"
            android:layout_marginTop="-64dp"
            >
        </androidx.recyclerview.widget.RecyclerView>

    </RelativeLayout>
    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/new_fab_note"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:backgroundTint="@color/colorAccent"
        app:layout_anchor="@id/note_recycler_view"
        app:layout_anchorGravity="bottom|right"
        android:layout_margin="16dp"
        android:tint="@android:color/black"
        android:src="@drawable/ic_add_playstore"
        app:maxImageSize="56dp"
        />

</androidx.coordinatorlayout.widget.CoordinatorLayout>

```

on ajoute des colors dans res > values > colors.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="black">#FF000000</color>
    <color name="white">#FFFFFFFF</color>
    <color name="colorPrimary">#8AC249</color>
    <color name="colorPrimaryDark">#679E38</color>
    <color name="colorAccent">#FE5622</color>
</resources>
```

new layout > note.xml ressource file note

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="70dp" >

    <androidx.cardview.widget.CardView
        android:id="@+id/note_cardview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:focusable="true"
        android:clickable="true"
        android:foreground="?attr/selectableItemBackground"
        >
        <TextView
            android:id="@+id/title_note"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" 
            tools:text="Ceci est le titre"
            style="@style/noteTitleFont"
            />
        <TextView
            android:id="@+id/excerpt"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            
            />

    </androidx.cardview.widget.CardView>

</RelativeLayout>

```

new values > styles.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <style name="noteTitleFont">
        <item name="android:textSize" >20sp</item>
        <item name="android:textStyle" >bold</item>
    </style>

</resources>
```

dans memo note detail.kt

```xml

```