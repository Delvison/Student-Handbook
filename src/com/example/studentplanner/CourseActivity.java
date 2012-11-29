package com.example.studentplanner;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CourseActivity extends ListActivity {
	String cName;
	String desc;
	String loc;
	int startHr;
	int startMin;
	int startYr;
	int startMth;
	int startDay;
	String occur;
	String semester;
	TextView courseName;
	TextView cDesc;
	TextView cTime;
	TextView cLoc;
	TextView cDays;
	double grade;
	SQLiteDatabase db;
	TextView gr;
	String[] evArr;
	int eventTot;
	double received = 0;
	double potential = 0;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_view2);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			cName = extras.getString("key");
		}
		this.initCourse(cName);
		courseName = (TextView) findViewById(R.id.textView1);
		courseName.setText(cName);
		this.popEvents();
		this.list();
		this.calcAssign();
		this.calcExam();
		this.calcGrade();
		int c = (int) grade;
		gr.setText("Grade: " + Integer.toString(c));

	}

	public void initCourse(String c) {

		// db.execSQL("CREATE TABLE IF NOT EXISTS Courses (CourseName VARCHAR, Description VARCHAR,"
		// +
		// " Location VARCHAR, HourStart INT, MinuteStart INT, YearStart INT, MonthStart INT, DayStart INT, "
		// +
		// " Occurences VARCHAR)");
		db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
		// get values for the current semester being viewed
		Cursor cursor = db.rawQuery("select * from Courses where CourseName ='"
				+ c + "'", null);
		// get those values
		cursor.moveToFirst();
		String name = cursor.getString(cursor.getColumnIndex("CourseName"));
		desc = cursor.getString(cursor.getColumnIndex("Description"));
		loc = cursor.getString(cursor.getColumnIndex("Location"));
		startHr = cursor.getInt(cursor.getColumnIndex("HourStart"));
		startMin = cursor.getInt(cursor.getColumnIndex("MinuteStart"));
		startYr = cursor.getInt(cursor.getColumnIndex("YearStart"));
		startMth = cursor.getInt(cursor.getColumnIndex("MonthStart"));
		startDay = cursor.getInt(cursor.getColumnIndex("DayStart"));
		occur = cursor.getString(cursor.getColumnIndex("Occurences"));
		semester = cursor.getString(cursor.getColumnIndex("Semester"));

		// close cursor and database
		cursor.close();
		db.close();
		// create a course object
		// Course co = new Course(name,desc,loc,true);
		// display information on the view

		cDesc = (TextView) findViewById(R.id.descView);
		cTime = (TextView) findViewById(R.id.timeView);
		cLoc = (TextView) findViewById(R.id.locView);
		cDays = (TextView) findViewById(R.id.dayView);
		cDesc.setText(desc);
		if (startMin == 0) {
			cTime.setText(Integer.toString(startHr) + ":00");

		} else {
			cTime.setText(Integer.toString(startHr) + ":"
					+ Integer.toString(startMin));
		}
		cLoc.setText(loc);
		cDays.setText(occur);
		gr = (TextView) findViewById(R.id.textView2);

	}

	public void clickHandler(View v) {
		Intent intent = new Intent(getApplicationContext(),
				AssignmentListviewActivity.class);
		intent.putExtra("key", cName);
		startActivity(intent);
	}

	public void examClick(View v) {
		Intent intent = new Intent(getApplicationContext(),
				ExamListviewActivity.class);
		intent.putExtra("key", cName);
		startActivity(intent);
	}

	public void notebookHandler(View v) {
		Intent intent = new Intent(getApplicationContext(),
				NotebookActivity.class);
		intent.putExtra("CourseName", cName);
		startActivity(intent);
	}

	public void progressClick(View v) {
		Intent intent = new Intent(getApplicationContext(),
				ProgressActivity.class);
		intent.putExtra("key", cName);
		startActivity(intent);
	}

	public void list() {
		String[] l = { "YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ",
				"YYYY/MM/DD)\n (Event Here)\n (days left) ", };
		if (eventTot != 0) {
		setListAdapter(new ArrayAdapter<String>(this,
				R.layout.custom_listview, evArr));// needs an array
		// get the list view from the view
		ListView listView = getListView();
		// set the listview's textfilter to enabled
		listView.setTextFilterEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.course_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addAssignment:
			startActivity(new Intent(this, CreateAssignmentActivity.class));
			finish();
			return true;
		case R.id.viewEvents:
			startActivity(new Intent(this, CreateCourseActivity.class));
			finish();
			return true;
		case R.id.editCourse:
			Intent i = new Intent(this, EditCourseActivity.class);
			i.putExtra("key", cName);
			startActivity(i);
			finish();
			return true;

		}
		return super.onOptionsItemSelected(item);
	}

	public void calcAssign() {
		try {
			SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE,
					null);
			Cursor assignCursor = db.rawQuery(
					"select * from Assignments where Course ='" + cName
							+ "' and Complete = 1", null);

			assignCursor.moveToFirst();
			while (assignCursor.isAfterLast() == false) {
				received = received
						+ assignCursor.getInt(assignCursor
								.getColumnIndex("PointsRecieved"));
				potential = potential
						+ assignCursor.getInt(assignCursor
								.getColumnIndex("MaxPoints"));
				assignCursor.moveToNext();
			}

		} catch (SQLiteException e) {
			e.printStackTrace();
		}
	}
	
	public void calcExam() {
		try {
			SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE,
					null);
			Cursor examCursor = db.rawQuery(
					"select * from Exams where Course ='" + cName
							+ "' and Complete = 1", null);
			
			examCursor.moveToFirst();
			while (examCursor.isAfterLast() == false) {
				received = received
						+ examCursor.getInt(examCursor
								.getColumnIndex("PointsRecieved"));
				potential = potential
						+ examCursor.getInt(examCursor
								.getColumnIndex("MaxPoints"));
				examCursor.moveToNext();
			}

		} catch (SQLiteException e) {
			e.printStackTrace();
		}
	}
	
	public void calcGrade() {
		if (potential == 0)	grade = 100;
		else grade = (received / potential) * 100;
	}
	
	public void update() {
		ContentValues values = new ContentValues();
		values.put("CourseName", cName);
		values.put("HourStart", startHr);
		values.put("MinuteStart", startMin);
		values.put("Description", desc);
		values.put("Location", loc);
		values.put("Occurences", occur);
		values.put("Semester", semester);
		values.put("Grade", grade);

		db.update("Courses", values, "CourseName=" + "'" + cName + "'", null);
	}

	public void popEvents() {
		int a=0, ex =0;
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
		
        eventTot = a + ex; //combine both counts
		evArr = new String[eventTot];
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
				s = s + "\n" + aCursor.getInt(aCursor.getColumnIndex("DueMonth")) + "/"
						+ aCursor.getInt(aCursor.getColumnIndex("DueDay")) + "/"
						+ aCursor.getInt(aCursor.getColumnIndex("DueYear"));

				evArr[count] = s;
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
				s = s + "\n" + eCursor.getInt(eCursor.getColumnIndex("DueMonth")) + "/"
						+ eCursor.getInt(eCursor.getColumnIndex("DueDay")) + "/"
						+ eCursor.getInt(eCursor.getColumnIndex("DueYear"));

				evArr[count] = s;
				// increment count
				count++;
				// move cursor by 1
				eCursor.moveToNext();
			}
			eCursor.close();
		}
	}
	
	protected void onResume(){
		super.onResume();
	}
}