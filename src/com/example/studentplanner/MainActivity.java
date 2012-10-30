package com.example.studentplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    Button createSem;

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if statement to have alternate views (if a semester doesnt exist then show a button to create one)
        setContentView(R.layout.main_view);
        createSem = (Button) findViewById(R.id.createSem);
    }

    public void handleClick(View v){
        Intent intent = new Intent(getApplicationContext(), CreateSemesterActivity.class);
        startActivity(intent);
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
