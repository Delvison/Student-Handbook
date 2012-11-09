package com.example.studentplanner;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class EditMiscActivity extends Activity {
	String mName;
	EditText mNameInView;
	DatePicker dateInView;
	TimePicker timeInView;
	EditText descInView;
	EditText locInView;
	Spinner reoccurInView;
	SQLiteDatabase db;
	String name;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_misc_view);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			mName = extras.getString("key");
		}
		this.initMisc(mName);
	}

	public void initMisc(String x) {
		// open database
		db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
		// get values for the current semester being viewed
		Cursor c = db.rawQuery("select * from Miscs where name ='" + x
				+ "'", null);
		// get those values
		c.moveToFirst();
		name = c.getString(c.getColumnIndex("MiscName"));
		String desc = c.getString(c.getColumnIndex("Description"));
		String loc = c.getString(c.getColumnIndex("Location"));
		int hour = c.getInt(c.getColumnIndex("HourStart"));
		int minute = c.getInt(c.getColumnIndex("MinuteStart"));
		int startYr = c.getInt(c.getColumnIndex("YearStart"));
	    int startMth = c.getInt(c.getColumnIndex("MonthStart"));
	    int startDay = c.getInt(c.getColumnIndex("DayStart"));
		String occur = c.getString(c.getColumnIndex("Occurences"));
		// close cursor and database
		c.close();

		mNameInView = (EditText) findViewById(R.id.editText1);
		mNameInView.setText(name);

		timeInView = (TimePicker) findViewById(R.id.timePicker1);
		timeInView.setCurrentHour(hour);
		timeInView.setCurrentMinute(minute);

		dateInView = (DatePicker) findViewById(R.id.datePicker1);
		dateInView.updateDate(startYr, startMth, startDay);
		
		descInView = (EditText) findViewById(R.id.editText2);
		descInView.setText(desc);
		
		locInView = (EditText) findViewById(R.id.editText3);
		locInView.setText(loc);

		reoccurInView = (Spinner) findViewById(R.id.spinner1);
		
		/*
		int p = 0;
		if (name.equals("MWF"))
			p = 0;
		if (name.equals("TH"))
			p = 1;
		if (name.equals("MW"))
			p = 2;
		if (name.equals("Weekly"))
			p = 3;
		reoccurInView.setSelection(p);		
		*/
	}

	public void handleClick(View v) {
		// replace values in db
		String nameGot = (String) mNameInView.getText().toString();
		int hourGot = timeInView.getCurrentHour();
		int minGot = timeInView.getCurrentMinute();
		int yearGot = dateInView.getYear();
		int monthGot = dateInView.getMonth();
		int dayGot = dateInView.getDayOfMonth();
		String descGot = (String) descInView.getText().toString();
		String locGot = (String) locInView.getText().toString();
		String occurGot = (String) reoccurInView.getSelectedItem().toString();

		ContentValues values = new ContentValues();
		values.put("MiscName", nameGot);
		values.put("HourStart", hourGot);
		values.put("MinuteStart", minGot);
		values.put("YearStart", yearGot);
		values.put("MonthStart", monthGot);
		values.put("DayStart", dayGot);
		values.put("Description", descGot);
		values.put("Location", locGot);
		values.put("Occurences", occurGot);

		db.update("Miscs", values, "Name=" + "'" + name + "'", null);
		// close db

		db.close();

		Intent intent = new Intent(getApplicationContext(),
				SemesterActivity.class);
		//intent.putExtra("key", course);
		startActivity(intent);
	}
	
	public void deleteHandler(View v) {
		db.delete("Miscs", "Name=" + "'" + mName + "'", null);
		db.close();

		Intent intent = new Intent(getApplicationContext(),
				SemesterActivity.class);
		//intent.putExtra("key", course);
		startActivity(intent);
	}
}
