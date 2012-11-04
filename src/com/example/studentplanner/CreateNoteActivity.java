package com.example.studentplanner;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CreateNoteActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_note_view, menu);
        return true;
    }
}
