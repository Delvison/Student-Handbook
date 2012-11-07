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
import android.widget.CheckBox;

public class EditAssignmentActivity extends Activity {
	String aName;
	EditText aNameInView;
	DatePicker dateInView;
	EditText descInView;
	EditText rcvdInView;
	EditText maxInView;
	EditText courseInView;
	CheckBox complete;
	Assignment as;
	SQLiteDatabase db;
	String course;
	String name;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_assignment_view);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			aName = extras.getString("key");
		}
		this.initAssignment(aName);
	}

	public void initAssignment(String a) {
		// open database
		db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
		// get values for the current semester being viewed
		Cursor c = db.rawQuery("select * from Assignments where name ='" + a
				+ "'", null);
		// get those values
		c.moveToFirst();
		name = c.getString(c.getColumnIndex("Name"));
		int dueYear = c.getInt(c.getColumnIndex("DueYear"));
		int dueMonth = c.getInt(c.getColumnIndex("DueMonth"));
		int dueDay = c.getInt(c.getColumnIndex("DueDay"));
		String desc = c.getString(c.getColumnIndex("Description"));
		int ptsRcvd = c.getInt(c.getColumnIndex("PointsRecieved"));
		int ptsMax = c.getInt(c.getColumnIndex("MaxPoints"));
		course = c.getString(c.getColumnIndex("Course"));
		int comp = c.getInt(c.getColumnIndex("Complete"));
		// close cursor and database
		c.close();

		aNameInView = (EditText) findViewById(R.id.editText1);
		aNameInView.setText(name);

		dateInView = (DatePicker) findViewById(R.id.datePicker1);
		dateInView.updateDate(dueYear, dueMonth, dueDay);

		rcvdInView = (EditText) findViewById(R.id.editText3);
		rcvdInView.setText(Integer.toString(ptsRcvd));

		maxInView = (EditText) findViewById(R.id.editText4);
		maxInView.setText(Integer.toString(ptsMax));

		descInView = (EditText) findViewById(R.id.editText5);
		descInView.setText(desc);

		complete = (CheckBox) findViewById(R.id.checkBox1);
		if (comp == 1) {
			complete.setChecked(true);
		} else {
			complete.setChecked(false);
		}
	}

	public void handleClick(View v) {
		// replace values in db
		String nameGot = (String) aNameInView.getText().toString();
		int yrGot = dateInView.getYear();
		int mnthGot = dateInView.getMonth();
		int dayGot = dateInView.getDayOfMonth();
		int pntsGot = Integer.parseInt(rcvdInView.getText().toString());
		int maxGot = Integer.parseInt(maxInView.getText().toString());
		String descGot = (String) descInView.getText().toString();

		ContentValues values = new ContentValues();
		values.put("Name", nameGot);
		values.put("DueYear", yrGot);
		values.put("DueMonth", mnthGot);
		values.put("DueDay", dayGot);
		values.put("Description", descGot);
		values.put("PointsRecieved", pntsGot);
		values.put("MaxPoints", maxGot);
		values.put("Course", course);
		if (complete.isChecked()) {
			values.put("Complete", 1);
		}else{
			values.put("Complete", 0);
		}

		db.update("Assignments", values, "Name=" + "'" + name + "'", null);
		// close db

		db.close();

		Intent intent = new Intent(getApplicationContext(),
				CourseActivity.class);
		intent.putExtra("key", course);
		startActivity(intent);
	}
	
	public void deleteHandler(View v) {
		db.delete("Assignments", "Name=" + "'" + aName + "'", null);
		db.close();

		Intent intent = new Intent(getApplicationContext(),
				CourseActivity.class);
		intent.putExtra("key", course);
		startActivity(intent);
	}
}
