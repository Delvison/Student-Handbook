package com.example.studentplanner;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
        //createSem = (Button) findViewById(R.id.button1);
    }

        public void handleClick(View v){
            
            //Create an intent to start the new activity.
               // Our intention is to start secondActivity
            Intent intent = new Intent();
            intent.setClass(this,SemesterCreate.class);
            startActivity(intent);
           }
        
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
