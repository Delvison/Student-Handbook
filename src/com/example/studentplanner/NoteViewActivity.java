package com.example.studentplanner;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NoteViewActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_view, menu);
        return true;
    }
}
