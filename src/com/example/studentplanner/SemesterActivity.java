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
	Bundle b;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.b = savedInstanceState;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.semester_view2);
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
		// this.popEvents();
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
					// do nothing
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
		String[] l;
		l = this.popAllEvents();
		//l[0] = Integer.toString(this.countAllEvents());
		setListAdapter(new ArrayAdapter<String>(this,
				R.layout.custom_listview, l));// needs an array
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
			// loop through each assignment. check date to see if its in the
			// future.
			// if it is, add it to the main array that will be used in the
			// listview

		} catch (SQLiteException e) {
			// state that there are no upcoming events
			assArr = new String[1];
			assArr[0] = "No Upcoming Events.";
		}
	}

	public String[] arrayOfCourses() {
		String[] courseArr; // holds all courses pertaining to the semester
		// open database
		SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE,
				null);

		try {
			Cursor c = db.rawQuery(
					"SELECT CourseName FROM Courses WHERE Semester ='" + sName
							+ "'", null);
			int i = c.getCount();
			courseArr = new String[i];
			c.moveToFirst();
			int count = 0;
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
			// close the cursor
			c.close();
			// loop through each assignment. check date to see if its in the
			// future.
			// if it is, add it to the main array that will be used in the
			// listview
		} catch (SQLiteException e) {
			courseArr = new String[1];
			courseArr[0]= "No Courses Exist"; 
			e.printStackTrace();
		}
		// now we have all of our courses
		return courseArr;
	}

	public int countAllEvents() {
		int eventCounter = 0; // keeps count of all events
		String[] courseArr = this.arrayOfCourses(); // holds all courses
													// pertaining to the
													// semester
		// open database
		SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE,
				null);

		// count up assignments belonging to these courses, this wont
		// execute if 0 courses exist
		Cursor b = null;
		for (int a = 0; a < courseArr.length; a++) {
			String currentCourse = courseArr[a];
			try {
				b = db.rawQuery("SELECT Name FROM Assignments WHERE Course ='"
						+ currentCourse + "'", null);
				eventCounter = eventCounter + b.getCount();
						b.close();

			} catch (SQLiteException e) {
				e.printStackTrace();
			}
		}

		// next count up exams belonging to these courses, this wont execute if
		// 0 courses exist
		Cursor d = null;
		for (int a = 0; a < courseArr.length; a++) {
			String currentCourse = courseArr[a];
			try {
				d = db.rawQuery("SELECT Name FROM Exams WHERE Course ='"
						+ currentCourse + "'", null);
				eventCounter = eventCounter + d.getCount();
						d.close();
			} catch (SQLiteException e) {
				e.printStackTrace();
			}
		}

		// next count up misc. events belonging to semester
		try {
			Cursor e = db.rawQuery("SELECT MiscName FROM Miscs WHERE Semester ='"
					+ sName + "'", null);
			eventCounter = eventCounter + e.getCount();
			e.close();
		} catch (SQLiteException e) {
			e.printStackTrace();
		}
		db.close();
		return eventCounter;
	}

	public String[] popAllEvents() {
		int total = this.countAllEvents();
		String[] allEvents = new String[total];// holds all events
		int posHolder = 0;// keeps track of position in the array
		SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE,
				null);

		// first populate the array with events belonging to the semester
		try {
			Cursor a = db.rawQuery("SELECT * FROM Miscs WHERE Semester ='"
					+ sName + "'", null);
			a.moveToFirst();
			while (a.isAfterLast() == false) {
				String s = "(Event)" + a.getString(a.getColumnIndex("MiscName"));
				 int m =1 + a.getInt(a.getColumnIndex("MonthStart")); 
				
				s = s + "\n" + m + "/"
						+ a.getInt(a.getColumnIndex("DayStart")) + "/"
						+ a.getInt(a.getColumnIndex("YearStart"));
				allEvents[posHolder] = s;
				// increment count
				posHolder++;
				// move cursor by 1
				a.moveToNext();
			}
			a.close();
		} catch (SQLiteException e) {
			e.printStackTrace();
		}

		// next populate the array with assignments belonging to courses of the
		// semester
		String[] courseArr = this.arrayOfCourses();

		Cursor b = null;
		for (int a = 0; a < courseArr.length; a++) {
			String currentCourse = courseArr[a];
			try {
				b = db.rawQuery("SELECT * FROM Assignments WHERE Course ='"
						+ currentCourse + "'", null);
				b.moveToFirst();
				while (b.isAfterLast() == false) {
					String s = "(Assign.) "+b.getString(b.getColumnIndex("Name"));
					int m = 1+ b.getInt(b.getColumnIndex("DueMonth"));
					s = s + "\n" + m + "/"
							+ b.getInt(b.getColumnIndex("DueDay")) + "/"
							+ b.getInt(b.getColumnIndex("DueYear"));
					allEvents[posHolder] = s;
					// increment count
					posHolder++;
					// move cursor by 1
					b.moveToNext();
					b.close();
				}
			} catch (SQLiteException e) {
				e.printStackTrace();
			}
		
		
		}
		// next populate the array with exams belonging to courses of the
		// semester
		Cursor c = null;
		for (int a = 0; a < courseArr.length; a++) {
			String currentCourse = courseArr[a];
			try {
				c = db.rawQuery("SELECT * FROM Exams WHERE Course ='"
						+ currentCourse + "'", null);
				c.moveToFirst();
				while (c.isAfterLast() == false) {
					String s = "(Exam) "+c.getString(c.getColumnIndex("Name"));
					int m = 1 + c.getInt(c.getColumnIndex("DueMonth"));
					s = s + "\n" + m + "/"
							+ c.getInt(c.getColumnIndex("DueDay")) + "/"
							+ c.getInt(c.getColumnIndex("DueYear"));
					allEvents[posHolder] = s;
					// increment count
					posHolder++;
					// move cursor by 1
					c.moveToNext();
					db.close();
				}
			} catch (SQLiteException e) {
				e.printStackTrace();
			}
		}
		//c.close();
		
		return allEvents;
	}
	
	public void onResume(){
		super.onResume();
		this.onCreate(b);
	}

}