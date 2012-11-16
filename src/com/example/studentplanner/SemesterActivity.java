package com.example.studentplanner;

import java.util.GregorianCalendar;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SemesterActivity extends ListActivity {
	String sName;
	String[] courseArr;
	String[] assArr;
	TextView sNameInView;
	TextView daysInView;
	long days;
	Button courses;
	Button Events;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.semester_view);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			sName = extras.getString("key");
		}
		// R.string.semesterString = sName;
		sNameInView = (TextView) findViewById(R.id.textView1);
		sNameInView.setText(sName);
		this.initSemester(sName);
		daysInView = (TextView) findViewById(R.id.textView2);
		if (days != 0) {
			daysInView.setText(Long.toString(days) + " days left.");
		} else {
			daysInView.setText("Semester Over.");
		}
		//this.popEvents();
		this.list();
	}

	public void initSemester(String sem) {
		// open database
		SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE,
				null);
		// get values for the current semester being viewed
		Cursor c = db.rawQuery("select * from Semesters where Session ='" + sem
				+ "'", null);
		// get those values
		c.moveToFirst();
		String name = c.getString(c.getColumnIndex("Session"));
		int startYear = c.getInt(c.getColumnIndex("YearStart"));
		int startMonth = c.getInt(c.getColumnIndex("MonthStart"));
		int startDay = c.getInt(c.getColumnIndex("DayStart"));
		int endYear = c.getInt(c.getColumnIndex("YearEnd"));
		int endMonth = c.getInt(c.getColumnIndex("MonthEnd"));
		int endDay = c.getInt(c.getColumnIndex("DayEnd"));
		// close cursor and database
		c.close();
		db.close();
		// create a semester object
		Semester s = new Semester(name, new GregorianCalendar(startYear,
				startMonth, startDay), new GregorianCalendar(endYear, endMonth,
				endDay));
		// calculate days toward end
		s.calculateDaysToEnd();
		days = s.daysTowardsEnd;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addAssignment:
			startActivity(new Intent(this, CreateAssignmentActivity.class));
			return true;
		case R.id.viewEvents:
			startActivity(new Intent(this, CreateCourseActivity.class));
			return true;
		case R.id.editSemester:
			Intent i = new Intent(this, EditSemesterActivity.class);
			i.putExtra("key", sName);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// button for course's handler
	public void clickHandler(View v) {
		final String[] items = { "Course1", "Course2", "Course3", "Course4",
				"Course5", "Course6" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Choose a course.");
		this.popCourses();
		builder.setItems(courseArr, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				// this string holds the list item clicked
				String rightMeow = courseArr[item];

				// When clicked, show a toast with the TextView text
				// Toast.makeText(getApplicationContext(),
				// ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
				// if item clicked equals add semester

				if (rightMeow.equals("No Courses Exist.")) {
                       //do nothing
				} else {
					Intent intent = new Intent(getApplicationContext(),
							CourseActivity.class);
					intent.putExtra("key", rightMeow);
					startActivity(intent);
				}

			}
		});
		AlertDialog alert = builder.create();

		alert.setButton("Add Course", new DialogInterface.OnClickListener() {

			// Click listener on the neutral button of alert box
			public void onClick(DialogInterface arg0, int arg1) {

				// The neutral button was clicked
				Intent intent = new Intent(getApplicationContext(),
						CreateCourseActivity.class);
				startActivity(intent);
				finish();
			}
		});
		alert.show();

		Intent intent = new Intent(getApplicationContext(),
				CourseListviewActivity.class);
		intent.putExtra("key", sName);
		// startActivity(intent);
	}

	public void miscClick(View v) {
		Intent intent = new Intent(getApplicationContext(),
				MiscListviewActivity.class);
		intent.putExtra("key", sName);
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
		setListAdapter(new ArrayAdapter<String>(this,
				R.layout.semester_listview, l));// needs an array
		// get the list view from the view
		ListView listView = getListView();
		// set the listview's textfilter to enabled
		listView.setTextFilterEnabled(true);
	}

	public void popCourses() {
		try {
			// open database
			SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE,
					null);
			// query. receive a cursor
			Cursor c = db.rawQuery("SELECT CourseName FROM Courses", null);
			// count how many items in cursor. add 1 to leave space for add
			// semester option
			int i = c.getCount();
			// instantiate array of semesters by size of the cursor + 1
			courseArr = new String[i];
			// set up a count int to keep track of array positions
			int count = 0;
			// move the cursor to first position
			c.moveToFirst();

			// while the cursor position isn't passed the last item in the
			// cursor
			while (c.isAfterLast() == false) {
				// store the string in "Session" column into the array of
				// semesters
				courseArr[count] = c.getString(c.getColumnIndex("CourseName"));
				// increment count
				count++;
				// move cursor by 1
				c.moveToNext();
			}
			// add an "add semester option
			// close the cursor
			c.close();
			// close the database
			db.close();
		} catch (SQLiteException e) {
			courseArr = new String[1];
			courseArr[0] = "No Courses Exist.";
		}
	}

	// populate events
	public void popEvents() {
		try {
			// open database
			SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE,
					null);
			Cursor c = db.rawQuery("SELECT Name FROM Assignments", null);
			int i = c.getCount();
		    assArr = new String[i];
			// set up a count int to keep track of array positions
			int count = 0;
			// move the cursor to first position
			c.moveToFirst();

			// while the cursor position isn't passed the last item in the
			// cursor
			while (c.isAfterLast() == false) {
				// store the string in "Session" column into the array of
				// semesters
				assArr[count] = c.getString(c.getColumnIndex("Name"));
				// increment count
				count++;
				// move cursor by 1
				c.moveToNext();
			}
			// close the cursor
			c.close();
			//loop through each assignment. check date to see if its in the future.
			//if it is, add it to the main array that will be used in the listview
			

		} catch (SQLiteException e) {
		//state that there are no upcoming events
			assArr = new String[1];
			assArr[1] = "No Upcoming Events.";
		}
	}
}