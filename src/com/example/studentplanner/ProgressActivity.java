package com.example.studentplanner;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class ProgressActivity extends ListActivity {
	String cName;
	String[] stringArr;
	String[] assignArr;
	String[] examArr;
	Spinner courses;
	SQLiteDatabase db;
	Course c;
	ProgressBar pro;
	int i; // count of both
	int grado;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_view);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			cName = extras.getString("key");
		}
		// popAssignment();
		// popExam();
		// bridge();
		getGrado();
		popEvents();
		setListAdapter(new ArrayAdapter<String>(this,
				R.layout.semester_listview, stringArr));
		pro = (ProgressBar) findViewById(R.id.progressBar1);
		pro.setMax(100);
		// this.getGrado();
		pro.setProgress(grado);

	}

	public void backHandler(View v) {
		finish();
	}

	public void popEvents() {
		int a = 0, ex = 0;
		Cursor aCursor = null;
		Cursor eCursor = null;
		SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE,
				null);
		try {
			aCursor = db.rawQuery("SELECT * FROM Assignments WHERE Course='"
					+ cName + "'", null);
			a = aCursor.getCount();

		} catch (SQLiteException e) {
			e.printStackTrace();
		}
		try {
			eCursor = db.rawQuery("SELECT * FROM Exams WHERE Course='" + cName
					+ "'", null);
			ex = eCursor.getCount();

		} catch (SQLiteException e) {
			e.printStackTrace();
		}

		stringArr = new String[(a + ex)];
		// set up a count int to keep track of array positions
		int count = 0;
		// move the cursor to first position
		if (aCursor != null) {
			aCursor.moveToFirst();
			// while the cursor position isn't passed the last item in the
			// cursor
			while (aCursor.isAfterLast() == false) {
				// store the string in "Session" column into the array of
				// semesters
				String s = aCursor.getString(aCursor.getColumnIndex("Name"));
				double temp1 = (double) aCursor.getInt(aCursor
						.getColumnIndex("PointsRecieved"));
				double temp2 = (double) aCursor.getInt(aCursor
						.getColumnIndex("MaxPoints"));
				double grade = (temp1 / temp2) * 100;
				s = s + " - " + Double.toString(grade);
				stringArr[count] = s;
				// increment count
				count++;
				// move cursor by 1
				aCursor.moveToNext();
			}
			aCursor.close();

		}

		if (eCursor != null) {
			eCursor.moveToFirst();
			// while the cursor position isn't passed the last item in the
			// cursor
			while (eCursor.isAfterLast() == false) {
				// store the string in "Session" column into the array of
				// semesters
				String s = eCursor.getString(eCursor.getColumnIndex("Name"));
				double temp1 = (double) eCursor.getInt(eCursor
						.getColumnIndex("PointsRecieved"));
				double temp2 = (double) eCursor.getInt(eCursor
						.getColumnIndex("MaxPoints"));
				double grade = (temp1 / temp2) * 100;
				s = s + " - " + Double.toString(grade);
				stringArr[count] = s;
				// increment count
				count++;
				// move cursor by 1
				eCursor.moveToNext();
			}
			eCursor.close();
		}
		db.close();
	}

	public void getGrado() {
		SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE,
				null);
		Cursor f = db.rawQuery("SELECT * FROM Courses WHERE CourseName='"
				+ cName + "'", null);
		f.moveToFirst();
		grado = f.getInt(f.getColumnIndex("Grade"));
		f.close();
		db.close();
	}
}
