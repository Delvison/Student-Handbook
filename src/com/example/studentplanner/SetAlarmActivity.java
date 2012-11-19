package com.example.studentplanner;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SetAlarmActivity extends Activity {
	String course;
	String title;
	Spinner spinner;
	int month, day, year, hour, minute;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_alarm_view);
        Bundle extras = getIntent().getExtras();
        course = extras.getString("key");
        spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.alarm_options_array, R.id.spinner1);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_set_alarm, menu);
        return true;
    }
    
    public void onHandle(){
    	Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
        //Intent intent = new Intent(getApplicationContext(), SetAlarmActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("month", month);
        intent.putExtra("day", day);
        intent.putExtra("year", year);
        intent.putExtra("hour", hour);
        intent.putExtra("minute", minute);
        startActivity(intent);
        finish();
    }
    
}
