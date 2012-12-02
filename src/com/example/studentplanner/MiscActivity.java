package com.example.studentplanner;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MiscActivity extends Activity {
	String mName;
	TextView miscName;
	TextView mDesc;
	TextView mTime;
	TextView mLoc;
	TextView mDays;
	TextView date;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.misc_view);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			mName = extras.getString("key");
		}
		this.initMisc(mName);
		miscName = (TextView) findViewById(R.id.textView1);
		miscName.setText(mName);

	}

	public void initMisc(String m) {

		// db.execSQL("CREATE TABLE IF NOT EXISTS Courses (CourseName VARCHAR, Description VARCHAR,"
		// +
		// " Location VARCHAR, HourStart INT, MinuteStart INT, YearStart INT, MonthStart INT, DayStart INT, "
		// +
		// " Occurences VARCHAR)");
		SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE,
				null);
		// get values for the current semester being viewed
		Cursor cursor = db.rawQuery("select * from Miscs where MiscName ='" + m
				+ "'", null);
		// get those values
		cursor.moveToFirst();
		String name = cursor.getString(cursor.getColumnIndex("MiscName"));
		String desc = cursor.getString(cursor.getColumnIndex("Description"));
		String loc = cursor.getString(cursor.getColumnIndex("Location"));
		int startHr = cursor.getInt(cursor.getColumnIndex("HourStart"));
		int startMin = cursor.getInt(cursor.getColumnIndex("MinuteStart"));
		int startYr = cursor.getInt(cursor.getColumnIndex("YearStart"));
		int startMth = cursor.getInt(cursor.getColumnIndex("MonthStart"));
		int startDay = cursor.getInt(cursor.getColumnIndex("DayStart"));
		String occur = cursor.getString(cursor.getColumnIndex("Occurences"));

		// boolean b = true;
		// if(occur.equals("Not Reoccuring")) b = false;

		// close cursor and database
		cursor.close();
		db.close();
		// create a course object
		// Misc mi = new Misc(name,desc,loc,b); //how are we getting the boolean
		// value now
		// display information on the view
		mDesc = (TextView) findViewById(R.id.textView2);
		mTime = (TextView) findViewById(R.id.textView4);
		mLoc = (TextView) findViewById(R.id.textView3);
		mDays = (TextView) findViewById(R.id.textView5);
		date = (TextView) findViewById(R.id.textView6);

		mDesc.setText(desc);
		mTime.setText(startHr + ":" + startMin);
		mLoc.setText(loc);
		mDays.setText(occur);
		date.setText(Integer.toString(startMth + 1) + "/"
				+ Integer.toString(startDay) + "/" + Integer.toString(startYr));
	}

	public void editHandler(View v) {
		Intent intent = new Intent(getApplicationContext(),
				EditMiscActivity.class);
		intent.putExtra("key", mName);
		startActivity(intent);
		finish();
	}
}
