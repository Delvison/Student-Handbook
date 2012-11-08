package com.example.studentplanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateSemesterActivity extends Activity {
	Button createSemester;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_semester_view);
		createSemester = (Button) findViewById(R.id.createSemester);
	}

	public void handleClick1(View v) {

		SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE,
				null);
		db.execSQL("CREATE TABLE IF NOT EXISTS Semesters (Session VARCHAR unique, YearStart INT,"
				+ " MonthStart INT, DayStart INT,YearEnd INT, MonthEnd INT, DayEnd INT )");

		// extract session from view
		Spinner sessionSpinner = (Spinner) findViewById(R.id.sessionSpinner);
		String session = (String) sessionSpinner.getSelectedItem();

		// extract start date from view
		DatePicker startDatePicker = (DatePicker) findViewById(R.id.semesterStartDate);
		int startDay = startDatePicker.getDayOfMonth();
		int startMonth = startDatePicker.getMonth();
		int startYear = startDatePicker.getYear();

		// extract end date from view
		DatePicker endDatePicker = (DatePicker) findViewById(R.id.semesterEndDate);
		int endDay = endDatePicker.getDayOfMonth();
		int endMonth = endDatePicker.getMonth();
		int endYear = endDatePicker.getYear();

		String sessionName = (String) session + " "
				+ Integer.toString(startYear);
		// Semester s = new Semester(sessionName,new
		// GregorianCalendar(startYear,startMonth,startDay),new
		// GregorianCalendar(endYear,endMonth,endDay));d
		ContentValues values = new ContentValues();

		values.put("Session", sessionName);

		values.put("YearStart", startYear);
		values.put("MonthStart", startMonth);
		values.put("DayStart", startDay);

		values.put("YearEnd", endYear);
		values.put("MonthEnd", endMonth);
		values.put("DayEnd", endDay);
		try {
			db.insert("Semesters", null, values);

			// db.execSQL("INSERT INTO Semesters VALUES(sessionName,startYear,startMonth,startDay,endYear,endMonth,endDay);");
			db.close();

			Intent intent = new Intent(getApplicationContext(),
					MainActivity.class);
			startActivity(intent);
			finish();
		} catch (SQLiteConstraintException e) {
			// Create the alert box
			AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

			// Set the message to display
			alertbox.setMessage("Error. Please make sure that Name is unique.");

			// Add a neutral button to the alert box and assign a click listener
			alertbox.setNeutralButton("OK.",
					new DialogInterface.OnClickListener() {

						// Click listener on the neutral button of alert box
						public void onClick(DialogInterface arg0, int arg1) {
						}
					});

			// show the alert box
			alertbox.show();
		}
	}

}
