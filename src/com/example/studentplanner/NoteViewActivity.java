package com.example.studentplanner;

import com.example.studentplanner.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.TextView;
import android.widget.EditText;

public class NoteViewActivity extends Activity {
	String cName;
	String nName;
	String nText;

	TextView courseName;
	TextView noteName;
	EditText noteText;
	Bundle extras;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_view);
		extras = getIntent().getExtras();

		courseName = (TextView) findViewById(R.id.textView1);
		noteName = (TextView) findViewById(R.id.textView2);
		noteText = (EditText) findViewById(R.id.editText1);

		if (extras != null) {
			cName = extras.getString("CourseName");
			courseName.setText(cName);

			nName = (String) extras.getString("NoteName");
			noteName.setText(nName);

		}
		initComponents();
	}

	public void initComponents() { 
		SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE,
				null);
		db.execSQL("CREATE TABLE IF NOT EXISTS Notes (CourseName VARCHAR, NoteName VARCHAR, Note VARCHAR)");

		Cursor cursor = db.rawQuery("select * from Notes where CourseName = '"+cName+"' where NoteName = '"+nName+"'", null);
		nText = cursor.getString(cursor.getColumnIndex("Note"));
		
		if(nText != null){
			noteText.setText(nText);
		}
		cursor.close();
		db.close();
	}

	public void doneHandle() {
		// Intent to go back to notebook_view
		//Intent intent = new Intent(getApplicationContext(),NotebookActivity.class);
		nText = noteText.getText().toString();
		
		SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
		db.execSQL("update Notes set Note='"+nText+"' where NoteName = '"+nName+"'");

		db.close();
		
		finish();

	}
}
