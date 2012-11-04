package com.example.studentplanner;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CreateNotebookActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_notebook_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_notebook_view, menu);
        return true;
    }
}
