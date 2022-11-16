package com.flintcore.my_notes_android;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.flintcore.my_notes_android.database.DatabaseManager;
import com.flintcore.my_notes_android.database.tables.Note;
import com.flintcore.my_notes_android.database.tables.Tables;
import com.flintcore.my_notes_android.listener.NoteViewSelectorListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Activities {

    public static Messages messages;
    private View layoutWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messages = new Messages();
        layoutWrapper = findViewById(R.id.body_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        DatabaseManager.getInstance().add(this, Tables.NOTES);

        fragmentManager.beginTransaction()
                .replace(R.id.body_main, new ViewListNotes())
                .commitNow();
    }

    public class Messages {
        public void toastMessage(@StringRes int text, int duration) {
            Toast.makeText(MainActivity.this.getApplicationContext(), text, duration).show();
        }

        public void snackBarMessage(@StringRes int text, int duration) {
            Snackbar.make(MainActivity.this.layoutWrapper, text, duration).show();
        }
    }
}