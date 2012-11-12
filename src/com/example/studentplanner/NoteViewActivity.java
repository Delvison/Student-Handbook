package com.example.studentplanner;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NoteViewActivity extends Activity {
	String cName;
	String nName;
	String nText;

	TextView courseName;
	TextView noteName;
	EditText noteText;
	Bundle extras;
	Button doneButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_view);
		extras = getIntent().getExtras();

		courseName = (TextView) findViewById(R.id.textView2);
		noteName = (TextView) findViewById(R.id.textView1);
		noteText = (EditText) findViewById(R.id.editText1);

		if (extras != null) {
			cName = extras.getString("CourseName");
			courseName.setText(cName);
			nName = (String) extras.getString("NoteName");
			noteName.setText(nName);

		}
		initComponents(cName);
	}

	public void initComponents(String c) { 
		SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE,
				null);
		//Cursor cursor = db.rawQuery("select Note from Notes where CourseName = '"+c+"' AND where NoteName = '"+nName+"'", null);
		Cursor cursor = db.rawQuery("select * from Notes where NoteName = '"+nName+"'", null);
		cursor.moveToFirst();
		nText = cursor.getString(cursor.getColumnIndex("Note"));
		
		if(nText != ""){
			noteText.setText(nText);
		}
		cursor.close();
		db.close();
		doneButton = (Button) findViewById(R.id.button1);
	}

	public void doneHandle(View v) {
		// Intent to go back to notebook_view
		//Intent intent = new Intent(getApplicationContext(),NotebookActivity.class);
		nText = noteText.getText().toString();
		
		SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
		db.execSQL("update Notes set Note='"+nText+"' where NoteName = '"+nName+"'");
		ContentValues values = new ContentValues();
        values.put("Note", nText);	
        
		db.update("Notes",values,"NoteName = '"+nName+"'",null);
		db.close();
		
		Intent intent = new Intent(getApplicationContext(), NotebookActivity.class);
		intent.putExtra("CourseName", cName);
		
		startActivity(intent);
		finish();

	}
}
