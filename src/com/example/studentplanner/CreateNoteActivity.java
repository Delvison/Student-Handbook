package com.example.studentplanner;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class CreateNoteActivity extends Activity {

	String courseName;
	String noteName;
	
	EditText nName;
	CheckBox cBox;
	
	boolean includeDate;
	Calendar date;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note_view);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
			courseName = extras.getString("CourseName");
		}  
        nName = (EditText) findViewById(R.id.editText1);
        cBox = (CheckBox) findViewById(R.id.checkBox1);
    }
    
  
    
    public void doneOnClick(View v){
    	noteName = nName.getText().toString();
    	int month;
    	int day;
    	int year;
    	
    	if(cBox.isChecked()){
    		date = Calendar.getInstance();
    		month = date.get(Calendar.MONTH) + 1;
    		day = date.get(Calendar.DAY_OF_MONTH);
    		year = date.get(Calendar.YEAR);
    		noteName += " Date: " + month + "/" + day + "/" + year; 
    	}

    	SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS Notes (CourseName VARCHAR, NoteName VARCHAR,Note VARCHAR)");
		//db.execSQL("INSERT INTO Notes VALUES('"+courseName+"','"+noteName+"',null)");
		ContentValues values = new ContentValues();  
		values.put("CourseName", courseName );
		values.put("NoteName", noteName );
		values.put("Note", "" );

        db.insert("Notes", null, values);

		db.close();
		
		Intent intent = new Intent(getApplicationContext(), NoteViewActivity.class);
		intent.putExtra("CourseName", courseName);
		intent.putExtra("NoteName",noteName);
		
		startActivity(intent);
    	finish();
    }

}
